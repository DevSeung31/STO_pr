package org.zerock.sto_pr.domain.order.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.account.entity.Account;
import org.zerock.sto_pr.domain.account.repository.AccountRepository;
import org.zerock.sto_pr.domain.member.entity.Member;
import org.zerock.sto_pr.domain.member.repository.MemberRepository;
import org.zerock.sto_pr.domain.order.dto.OrderCreateRequest;
import org.zerock.sto_pr.domain.order.entity.Order;
import org.zerock.sto_pr.domain.order.entity.OrderType;
import org.zerock.sto_pr.domain.order.repository.OrderRepository;
import org.zerock.sto_pr.domain.token.entity.Token;
import org.zerock.sto_pr.domain.token.entity.TokenHolding;
import org.zerock.sto_pr.domain.token.repository.TokenHoldingRepository;
import org.zerock.sto_pr.domain.token.repository.TokenRepository;

@Service
public class OrderCreateService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final AccountRepository accountRepository;
    private final TokenHoldingRepository tokenHoldingRepository;
    private final OrderRepository orderRepository;

    public OrderCreateService(MemberRepository memberRepository, TokenRepository tokenRepository, AccountRepository accountRepository, TokenHoldingRepository tokenHoldingRepository, OrderRepository orderRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
        this.accountRepository = accountRepository;
        this.tokenHoldingRepository = tokenHoldingRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Long create(OrderCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("member 없음"));

        Token token = tokenRepository.findById(request.tokenId())
                .orElseThrow(() -> new IllegalArgumentException("token 없음"));

        if (request.orderType() == OrderType.BUY) {
            Account account = accountRepository.findByMember_MemberId(request.memberId())
                    .orElseThrow(() -> new IllegalArgumentException("account 없음"));

            long totalPrice = request.orderPrice() * request.orderQuantity();
            account.lockBalance(totalPrice);
        } else {
            TokenHolding holding = tokenHoldingRepository
                    .findByMember_MemberIdAndToken_TokenId(request.memberId(), request.tokenId())
                    .orElseThrow(() -> new IllegalArgumentException("holding 없음"));

            holding.lockQuantity(request.orderQuantity());
        }

        Order order = new Order(
                member,
                token,
                request.orderPrice(),
                request.orderQuantity(),
                request.orderType(),
                System.currentTimeMillis()
        );
        orderRepository.save(order);
        return order.getOrderId();
    }
}
