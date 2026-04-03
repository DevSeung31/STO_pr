package org.zerock.sto_pr.domain.order.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.member.entity.Member;
import org.zerock.sto_pr.domain.token.entity.Token;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    @Column(name = "order_price", nullable = false)
    private Long orderPrice;

    @Column(name = "order_quantity", nullable = false)
    private Long orderQuantity;

    @Column(name = "filled_quantity", nullable = false)
    private Long filledQuantity = 0L;

    @Column(name = "remaining_quantity", nullable = false)
    private Long remainingQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_sequence", nullable = false)
    private Long orderSequence;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Order() {}

    public Order(Member member, Token token, Long orderPrice, Long orderQuantity, OrderType orderType, Long orderSequence) {
        this.member = member;
        this.token = token;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.remainingQuantity = orderQuantity;
        this.orderType = orderType;
        this.orderStatus = OrderStatus.OPEN;
        this.orderSequence = orderSequence;
    }

    public Long getOrderId() { return orderId; }
    public Member getMember() { return member; }
    public Token getToken() { return token; }
    public Long getOrderPrice() { return orderPrice; }
    public Long getRemainingQuantity() { return remainingQuantity; }
    public OrderType getOrderType() { return orderType; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public Long getOrderQuantity() { return orderQuantity; }

    public void fill(Long quantity) {
        this.filledQuantity += quantity;
        this.remainingQuantity -= quantity;
        this.updatedAt = LocalDateTime.now();

        if (remainingQuantity == 0) {
            this.orderStatus = OrderStatus.FILLED;
        } else {
            this.orderStatus = OrderStatus.PARTIAL;
        }
    }

    public void cancel() {
        if (this.orderStatus == OrderStatus.FILLED) {
            throw new IllegalStateException("완료 주문은 취소 불가");
        }
        this.orderStatus = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
}
