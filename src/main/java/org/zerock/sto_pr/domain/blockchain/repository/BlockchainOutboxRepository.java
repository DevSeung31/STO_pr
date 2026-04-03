package org.zerock.sto_pr.domain.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.blockchain.entity.BlockchainOutboxQ;
import org.zerock.sto_pr.domain.blockchain.entity.QueueStatus;

import java.util.List;

public interface BlockchainOutboxRepository extends JpaRepository<BlockchainOutboxQ, Long> {
    List<BlockchainOutboxQ> findTop10ByStatusOrderByQueueIdAsc(QueueStatus status);
}
