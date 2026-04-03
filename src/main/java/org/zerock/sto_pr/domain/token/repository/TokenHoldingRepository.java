package org.zerock.sto_pr.domain.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.token.entity.TokenHolding;

import java.util.Optional;

public interface TokenHoldingRepository extends JpaRepository<TokenHolding, Long> {
    Optional<TokenHolding> findByMember_MemberIdAndToken_TokenId(Long memberId, Long tokenId);
}
