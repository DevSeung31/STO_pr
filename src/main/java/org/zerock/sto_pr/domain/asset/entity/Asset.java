package org.zerock.sto_pr.domain.asset.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "total_value", nullable = false)
    private Long totalValue;

    @Column(name = "asset_address", nullable = false)
    private String assetAddress;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "is_allocated", nullable = false)
    private boolean allocated;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Asset() {}
}