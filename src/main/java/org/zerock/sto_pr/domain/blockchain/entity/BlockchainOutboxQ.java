package org.zerock.sto_pr.domain.blockchain.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.zerock.sto_pr.common.converter.JsonNodeConverter;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.trade.entity.Trade;

import java.time.LocalDateTime;

@Entity
@Table(name = "blockchain_outbox_q")
public class BlockchainOutboxQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_id")
    private Long queueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_token_holding_id", nullable = false)
    private PlatformTokenHolding platformTokenHolding;

    @Convert(converter = JsonNodeConverter.class)
    @Column(name = "payload_json", nullable = false, columnDefinition = "json")
    private JsonNode payloadJson;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private QueueStatus status;

    @Column(name = "retry_count", nullable = false)
    private int retryCount = 0;

    @Column(name = "last_error_message")
    private String lastErrorMessage;

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

    @Column(name = "max_retry", nullable = false)
    private int maxRetry = 3;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected BlockchainOutboxQ() {
    }

    public BlockchainOutboxQ(Trade trade, PlatformTokenHolding platformTokenHolding, JsonNode payloadJson, String idempotencyKey) {
        this.trade = trade;
        this.platformTokenHolding = platformTokenHolding;
        this.payloadJson = payloadJson;
        this.status = QueueStatus.PENDING;
        this.idempotencyKey = idempotencyKey;
        this.retryCount = 0;
        this.maxRetry = 3;
    }

    public Long getQueueId() { return queueId; }
    public Trade getTrade() { return trade; }
    public PlatformTokenHolding getPlatformTokenHolding() { return platformTokenHolding; }
    public JsonNode getPayloadJson() { return payloadJson; }
    public QueueStatus getStatus() { return status; }

    public void markProcessing() {
        this.status = QueueStatus.PROCESSING;
        this.updatedAt = LocalDateTime.now();
    }

    public void markConfirmed() {
        this.status = QueueStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    public void markFailed(String message) {
        this.status = QueueStatus.FAILED;
        this.retryCount += 1;
        this.lastErrorMessage = message;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isFinished() {
        return this.status == QueueStatus.CONFIRMED;
    }
}
