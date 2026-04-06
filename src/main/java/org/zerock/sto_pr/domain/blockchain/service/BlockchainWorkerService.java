package org.zerock.sto_pr.domain.blockchain.service;

import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainOutboxQ;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainTx;
import org.zerock.sto_pr.domain.blockchain.entity.QueueStatus;
import org.zerock.sto_pr.domain.blockchain.repository.BlockchainOutboxRepository;
import org.zerock.sto_pr.domain.blockchain.repository.BlockchainTxRepository;
import org.zerock.sto_pr.domain.trade.entity.Trade;
import org.zerock.sto_pr.domain.trade.repository.TradeRepository;

import java.util.List;

@Service
public class BlockchainWorkerService {

    private final BlockchainOutboxRepository blockchainOutboxRepository;
    private final BlockchainTxRepository blockchainTxRepository;
    private final TradeRepository tradeRepository;
    private final ContractGatewayService contractGatewayService;

    public BlockchainWorkerService(BlockchainOutboxRepository blockchainOutboxRepository, BlockchainTxRepository blockchainTxRepository, TradeRepository tradeRepository, ContractGatewayService contractGatewayService) {
        this.blockchainOutboxRepository = blockchainOutboxRepository;
        this.blockchainTxRepository = blockchainTxRepository;
        this.tradeRepository = tradeRepository;
        this.contractGatewayService = contractGatewayService;
    }

    @Transactional
    public void processPending() {
        List<BlockchainOutboxQ> list = blockchainOutboxRepository.findTop10ByStatusOrderByQueueIdAsc(QueueStatus.PENDING);

        for (BlockchainOutboxQ outbox : list) {
            processOne(outbox);
        }
    }

    @Transactional
    public void processOne(BlockchainOutboxQ outbox) {
        if (outbox.isFinished()) {
            return;
        }

        Trade trade = tradeRepository.findById(outbox.getTrade().getTradeId())
                .orElseThrow(() -> new IllegalArgumentException("trade 없으"));

        try {
            outbox.markProcessing();

            JsonNode payload = outbox.getPayloadJson();

            ContractGatewayService.TradeRecordResult result =
                    contractGatewayService.recordTrade(
                            trade.getToken().getContractAddress(),
                            payload.get("tradeId").asLong(),
                            payload.get("sellerAddress").asText(),
                            payload.get("buyerAddress").asText(),
                            payload.get("quantity").asLong(),
                            payload.get("price").asLong()
                    );

            BlockchainTx tx = BlockchainTx.tradeTx(
                    outbox,
                    trade,
                    outbox.getPlatformTokenHolding(),
                    result.txHash(),
                    payload.get("sellerAddress").asText(),
                    payload.get("buyerAddress").asText(),
                    trade.getToken().getContractAddress(),
                    result.gasUsed(),
                    result.blockNumber()
            );

            blockchainTxRepository.save(tx);

            outbox.markConfirmed();
            trade.markSuccess();
        } catch (Exception e) {
            outbox.markFailed(e.getMessage());
            trade.markFailed();
        }
    }
}
