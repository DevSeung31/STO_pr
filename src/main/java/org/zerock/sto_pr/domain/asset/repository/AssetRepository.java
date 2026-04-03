package org.zerock.sto_pr.domain.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.asset.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
