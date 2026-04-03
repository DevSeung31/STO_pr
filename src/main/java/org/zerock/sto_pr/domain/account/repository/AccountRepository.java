package org.zerock.sto_pr.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.account.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMember_MemberId(Long memberMemberId);
}
