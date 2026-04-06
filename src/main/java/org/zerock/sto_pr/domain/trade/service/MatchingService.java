package org.zerock.sto_pr.domain.trade.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.account.entity.Account;
import org.zerock.sto_pr.domain.account.repository.AccountRepository;
import org.zerock.sto_pr.domain.blockchain.service.ContractGatewayService;
import org.zerock.sto_pr.domain.order.entity.Order;
import org.zerock.sto_pr.domain.order.entity.OrderStatus;
import org.zerock.sto_pr.domain.order.entity.OrderType;
import org.zerock.sto_pr.domain.order.repository.OrderRepository;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.token.entity.TokenHolding;
import org.zerock.sto_pr.domain.token.repository.PlatformTokenHoldingRepository;
import org.zerock.sto_pr.domain.token.repository.TokenHoldingRepository;
import org.zerock.sto_pr.domain.trade.entity.Trade;
import org.zerock.sto_pr.domain.trade.repository.TradeRepository;

import java.util.List;

@Service
public class MatchingService {

    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;
    private final AccountRepository accountRepository;
    private final TokenHoldingRepository tokenHoldingRepository;
    private final PlatformTokenHoldingRepository platformTokenHoldingRepository;
    private final TradeOutboxService tradeOutboxService;

    public MatchingService(OrderRepository orderRepository, TradeRepository tradeRepository, AccountRepository accountRepository, TokenHoldingRepository tokenHoldingRepository, PlatformTokenHoldingRepository platformTokenHoldingRepository, TradeOutboxService tradeOutboxService) {
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
        this.accountRepository = accountRepository;
        this.tokenHoldingRepository = tokenHoldingRepository;
        this.platformTokenHoldingRepository = platformTokenHoldingRepository;
        this.tradeOutboxService = tradeOutboxService;
    }

    @Transactional
    public Long matchOne(Long tokenId) {
        List<Order> buys = orderRepository.findByToken_TokenIdAndOrderTypeAndOrderStatusOrderBySequenceAsc(
                tokenId, OrderType.BUY, OrderStatus.OPEN
        );

        List<Order> sells = orderRepository.findByToken_TokenIdAndOrderTypeAndOrderStatusOrderBySequenceAsc(
                tokenId, OrderType.SELL, OrderStatus.OPEN
        );

        if (buys.isEmpty() || sells.isEmpty()) {
            throw new IllegalStateException("매칭 가능한 주문이 없음");
        }
        Order buyOrder = buys.get(0);
        Order sellOrder = sells.get(0);

        if (buyOrder.getOrderPrice() < sellOrder.getOrderPrice()) {
            throw new IllegalStateException("가격 조건 불일치");
        }

        long matchQuantity = Math.min(buyOrder.getRemainingQuantity(), sellOrder.getRemainingQuantity());
        long tradePrice = sellOrder.getOrderPrice();

        buyOrder.fill(matchQuantity);
        sellOrder.fill(matchQuantity);

        Account buyerAccount = accountRepository.findByMember_MemberId(buyOrder.getMember().getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("buyer account 없음"));
        Account sellerAccount = accountRepository.findByMember_MemberId(sellOrder.getMember().getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("seller account 없음"));

        TokenHolding buyerHolding = tokenHoldingRepository
                .findByMember_MemberIdAndToken_TokenId(buyOrder.getMember().getMemberId(), tokenId)
                .orElseGet(() -> tokenHoldingRepository.save(new TokenHolding(buyOrder.getMember(), buyOrder.getToken())));

        TokenHolding sellerHolding = tokenHoldingRepository
                .findByMember_MemberIdAndToken_TokenId(sellOrder.getMember().getMemberId(), tokenId)
                .orElseThrow(() -> new IllegalArgumentException("seller holding 없음"));

        long totalAmount = tradePrice * matchQuantity;

        buyerAccount.unlockBalance(totalAmount);
        buyerAccount.debitAvailable(totalAmount);
        sellerAccount.creditAvailable(totalAmount);

        sellerHolding.unlockQuantity(matchQuantity);
        sellerHolding.decreaseQuantity(matchQuantity);
        buyerHolding.increaseQuantity(matchQuantity);

        Trade trade = new Trade(
                sellOrder.getMember(),
                buyOrder.getMember(),
                sellOrder,
                buyOrder,
                buyOrder.getToken(),
                tradePrice,
                matchQuantity
        );

        tradeRepository.save(trade);

        PlatformTokenHolding platformTokenHolding = platformTokenHoldingRepository.findByToken_TokenId(tokenId)
                .orElseThrow(() -> new IllegalArgumentException("platform token holding 없음"));

        tradeOutboxService.enqueue(trade.getTradeId(), platformTokenHolding.getPlatformTokenHoldingId());

        return trade.getTradeId();
    }
}
