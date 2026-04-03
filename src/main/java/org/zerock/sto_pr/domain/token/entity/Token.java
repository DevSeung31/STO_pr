package org.zerock.sto_pr.domain.token.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.asset.entity.Asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "total_supply", nullable = false)
    private Long totalSupply;

    @Column(name = "circulating_supply", nullable = false)
    private Long circulatingSupply;

    @Column(name = "token_name", nullable = false)
    private String tokenName;

    @Column(name = "token_symbol", nullable = false)
    private String tokenSymbol;

    @Column(name = "contract_address")
    private String contractAddress;

    @Column(name = "token_decimals")
    private Long tokenDecimals;

    @Column(name = "init_price", nullable = false)
    private Long initPrice;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_status", nullable = false)
    private TokenStatus tokenStatus;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt = LocalDateTime.now();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Token() {
    }

    public Token(
            Asset asset,
            Long totalSupply,
            Long circulatingSupply,
            String tokenName,
            String tokenSymbol,
            String contractAddress,
            Long tokenDecimals,
            Long initPrice,
            BigDecimal currentPrice,
            TokenStatus tokenStatus
    ) {
        this.asset = asset;
        this.totalSupply = totalSupply;
        this.circulatingSupply = circulatingSupply;
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
        this.contractAddress = contractAddress;
        this.tokenDecimals = tokenDecimals;
        this.initPrice = initPrice;
        this.currentPrice = currentPrice;
        this.tokenStatus = tokenStatus;
    }

    public Long getTokenId() { return tokenId; }
    public String getContractAddress() { return contractAddress; }
    public String getTokenName() { return tokenName; }
    public String getTokenSymbol() { return tokenSymbol; }
    public Long getTotalSupply() { return totalSupply; }
}
