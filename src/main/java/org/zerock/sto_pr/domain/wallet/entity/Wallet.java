package org.zerock.sto_pr.domain.wallet.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zerock.sto_pr.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "wallet_address", nullable = false, unique = true)
    private String walletAddress;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "wallet_type", nullable = false, columnDefinition = "wallet_type_enum")
    private WalletType walletType;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "wallet_status", nullable = false, columnDefinition = "wallet_status_enum")
    private WalletStatus walletStatus;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "wallet_role", nullable = false, columnDefinition = "wallet_role_enum")
    private WalletRole walletRole;

    @Column(name = "encrypted_private_key")
    private String encryptedPrivateKey;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Wallet() {
    }

    public Wallet(
            Member member,
            String walletAddress,
            WalletType walletType,
            WalletStatus walletStatus,
            WalletRole walletRole,
            String encryptedPrivateKey
    ) {
        this.member = member;
        this.walletAddress = walletAddress;
        this.walletType = walletType;
        this.walletStatus = walletStatus;
        this.walletRole = walletRole;
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public Long getWalletId() { return walletId; }
    public Member getMember() { return member; }
    public String getWalletAddress() { return walletAddress; }
    public WalletType getWalletType() { return walletType; }
    public WalletStatus getWalletStatus() { return walletStatus; }
    public WalletRole getWalletRole() { return walletRole; }
    public String getEncryptedPrivateKey() { return encryptedPrivateKey; }

    public void touchUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
