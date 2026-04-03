package org.zerock.sto_pr.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
