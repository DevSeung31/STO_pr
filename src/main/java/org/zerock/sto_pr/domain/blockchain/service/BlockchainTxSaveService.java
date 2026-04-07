package org.zerock.sto_pr.domain.blockchain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainOutboxQ;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainTx;
import org.zerock.sto_pr.domain.blockchain.entity.TxStatus;
import org.zerock.sto_pr.domain.blockchain.entity.TxType;
import org.zerock.sto_pr.domain.blockchain.repository.BlockchainTxRepository;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.trade.entity.Trade;

@Service
public class BlockchainTxSaveService {

    private final BlockchainTxRepository blockchainTxRepository;

    public BlockchainTxSaveService(BlockchainTxRepository blockchainTxRepository) {
        this.blockchainTxRepository = blockchainTxRepository;
    }

    @Transactional
    public BlockchainTx saveTradeTx(
         BlockchainOutboxQ queue,
         Trade trade,
         PlatformTokenHolding platformTokenHolding,
         String fromAddress,
         String toAddress,
         String contractAddress,
         ContractGatewayService.TradeRecordResult result
    ) {
        BlockchainTx tx = BlockchainTx.tradeTx(
               queue,
                trade,
               platformTokenHolding,
               result.txHash(),
               fromAddress,
               toAddress,
               contractAddress,
               result.gasUsed(),
               result.blockNumber()
        );
        return blockchainTxRepository.save(tx);
    }

    @Transactional
    public BlockchainTx saveDeployTx(
            PlatformTokenHolding platformTokenHolding,
            ContractGatewayService.DeployResult deployResult
    ) {
        BlockchainTx tx = BlockchainTx.deployTx(
                platformTokenHolding,
                deployResult.txHash(),
                deployResult.contractAddress()
        );
        return blockchainTxRepository.save(tx);
    }
}
