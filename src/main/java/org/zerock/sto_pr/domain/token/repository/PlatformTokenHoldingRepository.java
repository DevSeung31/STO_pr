package org.zerock.sto_pr.domain.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;

import java.util.Optional;

public interface PlatformTokenHoldingRepository extends JpaRepository<PlatformTokenHolding, Long> {
    Optional<PlatformTokenHolding> findByToken_TokenId(Long tokenId);
}
