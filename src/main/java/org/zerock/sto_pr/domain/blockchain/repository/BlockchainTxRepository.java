package org.zerock.sto_pr.domain.blockchain.repository;

import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainTx;

public interface BlockchainTxRepository extends JpaRepository<BlockchainTx, Long> {
}
