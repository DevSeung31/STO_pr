package org.zerock.sto_pr.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
}
