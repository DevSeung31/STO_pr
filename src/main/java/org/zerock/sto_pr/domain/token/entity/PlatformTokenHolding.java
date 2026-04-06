package org.zerock.sto_pr.domain.token.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.admin.entity.Admin;

import java.time.LocalDateTime;

@Entity
@Table(name = "platform_token_holdings")
public class PlatformTokenHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_token_holding_id")
    private Long platformTokenHoldingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    @Column(name = "holding_supply", nullable = false)
    private Long holdingSupply;

    @Column(name = "init_price", nullable = false)
    private Long initPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected PlatformTokenHolding() {
    }

    public PlatformTokenHolding(Admin admin, Token token, Long holdingSupply, Long initPrice) {
        this.admin = admin;
        this.token = token;
        this.holdingSupply = holdingSupply;
        this.initPrice = initPrice;
    }

    public Long getPlatformTokenHoldingId() { return platformTokenHoldingId; }
    public Token getToken() { return token; }

    public void decrease(Long quantity) {
        if (holdingSupply < quantity) throw new IllegalStateException("플랫폼 보유량 부족");
        this.holdingSupply -= quantity;
    }
}
