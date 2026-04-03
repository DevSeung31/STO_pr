package org.zerock.sto_pr.domain.admin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "admin_login_id", nullable = false)
    private String adminLoginId;

    @Column(name = "admin_login_password", nullable = false)
    private String adminLoginPassword;

    protected Admin() {}

    public Long getAdminId() { return adminId; }
}