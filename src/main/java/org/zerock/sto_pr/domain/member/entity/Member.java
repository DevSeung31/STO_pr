package org.zerock.sto_pr.domain.member.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "member_password", nullable = false)
    private String memberPassword;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Member() {
    }

    public Member(String email, String memberPassword, String memberName) {
        this.email = email;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }

    public Long getMemberId() { return memberId; }
    public String getEmail() { return email; }
    public String getMemberPassword() { return memberPassword; }
    public String getMemberName() { return memberName; }
}
