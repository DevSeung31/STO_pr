package org.zerock.sto_pr.domain.trade.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zerock.sto_pr.domain.member.entity.Member;
import org.zerock.sto_pr.domain.order.entity.Order;
import org.zerock.sto_pr.domain.token.entity.Token;

import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long tradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Member seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Member buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_order_id", nullable = false)
    private Order sellOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_order_id", nullable = false)
    private Order buyOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    @Column(name = "trade_price", nullable = false)
    private Long tradePrice;

    @Column(name = "trade_quantity", nullable = false)
    private Long tradeQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "settlement_status", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private SettlementStatus settlementStatus;

    @Column(name = "executed_at", nullable = false)
    private LocalDateTime executedAt;

    @Column(name = "fee_amount", nullable = false)
    private Long feeAmount = 0L;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Trade() {
    }

    public Trade(Member seller, Member buyer, Order sellOrder, Order buyOrder, Token token,
                 Long tradePrice, Long tradeQuantity) {
        this.seller = seller;
        this.buyer = buyer;
        this.sellOrder = sellOrder;
        this.buyOrder = buyOrder;
        this.token = token;
        this.tradePrice = tradePrice;
        this.tradeQuantity = tradeQuantity;
        this.settlementStatus = SettlementStatus.ON_CHAIN_PENDING;
        this.executedAt = LocalDateTime.now();
    }

    public Long getTradeId() { return tradeId; }
    public Member getSeller() { return seller; }
    public Member getBuyer() { return buyer; }
    public Token getToken() { return token; }
    public Long getTradePrice() { return tradePrice; }
    public Long getTradeQuantity() { return tradeQuantity; }

    public void markSuccess() {
        this.settlementStatus = SettlementStatus.SUCCESS;
    }

    public void markFailed() {
        this.settlementStatus = SettlementStatus.FAILED;
    }
}
