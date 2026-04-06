package org.zerock.sto_pr.domain.trade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.common.util.IdempotencyKeyGenerator;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainOutboxQ;
import org.zerock.sto_pr.domain.blockchain.repository.BlockchainOutboxRepository;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.token.repository.PlatformTokenHoldingRepository;
import org.zerock.sto_pr.domain.trade.entity.Trade;
import org.zerock.sto_pr.domain.trade.repository.TradeRepository;
import org.zerock.sto_pr.domain.wallet.entity.WalletStatus;
import org.zerock.sto_pr.domain.wallet.repository.WalletRepository;


@Service
public class TradeOutboxService {

    private final TradeRepository tradeRepository;
    private final PlatformTokenHoldingRepository platformTokenHoldingRepository;
    private final WalletRepository walletRepository;
    private final BlockchainOutboxRepository blockchainOutboxRepository;
    private final ObjectMapper objectMapper;
    private final IdempotencyKeyGenerator idempotencyKeyGenerator;


    public TradeOutboxService(TradeRepository tradeRepository, PlatformTokenHoldingRepository platformTokenHoldingRepository, WalletRepository walletRepository, BlockchainOutboxRepository blockchainOutboxRepository, ObjectMapper objectMapper, IdempotencyKeyGenerator idempotencyKeyGenerator) {
        this.tradeRepository = tradeRepository;
        this.platformTokenHoldingRepository = platformTokenHoldingRepository;
        this.walletRepository = walletRepository;
        this.blockchainOutboxRepository = blockchainOutboxRepository;
        this.objectMapper = objectMapper;
        this.idempotencyKeyGenerator = idempotencyKeyGenerator;
    }

    @Transactional
    public void enqueue(Long tradeId, Long platformTokenHoldingId) {

        Trade trade = tradeRepository.findById(tradeId)
                .orElseThrow(() -> new IllegalArgumentException("trade 없음"));

        PlatformTokenHolding platformTokenHolding = platformTokenHoldingRepository.findById(platformTokenHoldingId)
                .orElseThrow(() -> new IllegalArgumentException("plateform holding 없음"));

        String sellerAddress = walletRepository
                .findByMember_MemberIdAndWalletStatus(trade.getSeller().getMemberId(), WalletStatus.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("seller wallet 없음"))
                .getWalletAddress();

        String buyerAddress = walletRepository
                .findByMember_MemberIdAndWalletStatus(trade.getBuyer().getMemberId(), WalletStatus.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("buyer wallet 없음"))
                .getWalletAddress();

        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("tradeId", trade.getTradeId());
        payload.put("sellerAddress", sellerAddress);
        payload.put("buyerAddress", buyerAddress);
        payload.put("tokenId", trade.getToken().getTokenId());
        payload.put("price", trade.getTradePrice());
        payload.put("quantity", trade.getTradeQuantity());

        BlockchainOutboxQ outbox = new BlockchainOutboxQ(
                trade,
                platformTokenHolding,
                payload,
                idempotencyKeyGenerator.tradeKey(tradeId)
        );
        blockchainOutboxRepository.save(outbox);


    }
}
