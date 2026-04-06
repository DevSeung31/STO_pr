package org.zerock.sto_pr.domain.account.entity;

import jakarta.persistence.*;
import org.zerock.sto_pr.domain.member.entity.Member;


import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "account_password", nullable = false)
    private String accountPassword;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "available_balance", nullable = false)
    private Long availableBalance = 0L;

    @Column(name = "locked_balance", nullable = false)
    private Long lockedBalance = 0L;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Account() {
    }

    public Account(Member member, String accountPassword, String accountNumber) {
        this.member = member;
        this.accountPassword = accountPassword;
        this.accountNumber = accountNumber;
    }

    public Long getAccountId() { return accountId; }
    public Member getMember() { return member; }
    public Long getAvailableBalance() { return availableBalance; }
    public Long getLockedBalance() { return lockedBalance; }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountPassword() {
        return accountPassword;
    }

    public void lockBalance(Long amount) {
        if (availableBalance < amount) {
            throw new IllegalStateException("잔액 부족");
        }
        this.availableBalance -= amount;
        this.lockedBalance += amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void unlockBalance(Long amount) {
        if (lockedBalance < amount) {
            throw new IllegalStateException("잠금 잔액 부족");
        }
        this.lockedBalance -= amount;
        this.availableBalance += amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void debitAvailable(Long amount) {
        if (availableBalance < amount) {
            throw new IllegalStateException("잔액 부족");
        }
        this.availableBalance -= amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void creditAvailable(Long amount) {
        this.availableBalance += amount;
        this.updatedAt = LocalDateTime.now();
    }

}