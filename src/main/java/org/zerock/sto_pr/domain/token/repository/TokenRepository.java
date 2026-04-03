package org.zerock.sto_pr.domain.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.token.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
