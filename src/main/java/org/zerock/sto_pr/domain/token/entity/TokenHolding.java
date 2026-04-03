package org.zerock.sto_pr.domain.token.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.member.entity.Member;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "token_holdings",
        uniqueConstraints = @UniqueConstraint(name = "uq_token_holdings", columnNames = {"member_id", "token_id"})
)
public class TokenHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_holding_id")
    private Long tokenHoldingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    @Column(name = "current_quantity", nullable = false)
    private Long currentQuantity = 0L;

    @Column(name = "locked_quantity", nullable = false)
    private Long lockedQuantity = 0L;

    @Column(name = "avg_buy_price", nullable = false)
    private BigDecimal avgBuyPrice = BigDecimal.ZERO;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected TokenHolding() {
    }

    public TokenHolding(Member member, Token token) {
        this.member = member;
        this.token = token;
    }

    public Long getCurrentQuantity() { return currentQuantity; }
    public Long getLockedQuantity() { return lockedQuantity; }

    public void lockQuantity(Long quantity) {
        if (currentQuantity - lockedQuantity < quantity) {
            throw new IllegalStateException("매도 가능 수량 부족");
        }
        this.lockedQuantity += quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void unlockQuantity(Long quantity) {
        if (lockedQuantity < quantity) {
            throw new IllegalStateException("잠금 수량 부족");
        }
        this.lockedQuantity -= quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseQuantity(Long quantity) {
        this.currentQuantity += quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void decreaseQuantity(Long quantity) {
        if (currentQuantity < quantity) {
            throw new IllegalStateException("보유 수량 부족");
        }
        this.currentQuantity -= quantity;
        this.updatedAt = LocalDateTime.now();
    }
}