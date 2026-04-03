package org.zerock.sto_pr.domain.blockchain.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.trade.entity.Trade;

import java.time.LocalDateTime;

@Entity
@Table(name = "blockchain_tx")
public class BlockchainTx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tx_id")
    private Long txId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    private BlockchainOutboxQ queue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_token_holding_id", nullable = false)
    private PlatformTokenHolding platformTokenHolding;

    @Column(name = "tx_hash")
    private String txHash;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "contract_address", nullable = false)
    private String contractAddress;

    @Column(name = "gas_used")
    private Long gasUsed;

    @Column(name = "block_number")
    private Long blockNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "tx_status", nullable = false)
    private TxStatus txStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "tx_type", nullable = false)
    private TxType txType;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    protected BlockchainTx() {
    }

    public static BlockchainTx tradeTx(
            BlockchainOutboxQ queue,
            Trade trade,
            PlatformTokenHolding platformTokenHolding,
            String txHash,
            String fromAddress,
            String toAddress,
            String contractAddress,
            Long gasUsed,
            Long blockNumber
    ) {
        BlockchainTx tx = new BlockchainTx();
        tx.queue = queue;
        tx.trade = trade;
        tx.platformTokenHolding = platformTokenHolding;
        tx.txHash = txHash;
        tx.fromAddress = fromAddress;
        tx.toAddress = toAddress;
        tx.contractAddress = contractAddress;
        tx.gasUsed = gasUsed;
        tx.blockNumber = blockNumber;
        tx.txStatus = TxStatus.CONFIRMED;
        tx.txType = TxType.TRADE;
        tx.confirmedAt = LocalDateTime.now();
        return tx;
    }

    public static BlockchainTx deployTx(
            Trade trade,
            PlatformTokenHolding platformTokenHolding,
            String txHash,
            String contractAddress
    ) {
        BlockchainTx tx = new BlockchainTx();
        tx.trade = trade;
        tx.platformTokenHolding = platformTokenHolding;
        tx.txHash = txHash;
        tx.contractAddress = contractAddress;
        tx.txStatus = TxStatus.CONFIRMED;
        tx.txType = TxType.DEPLOY;
        tx.confirmedAt = LocalDateTime.now();
        return tx;
    }
}
