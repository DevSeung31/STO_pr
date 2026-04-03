package org.zerock.sto_pr.domain.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.wallet.entity.Wallet;
import org.zerock.sto_pr.domain.wallet.entity.WalletRole;
import org.zerock.sto_pr.domain.wallet.entity.WalletStatus;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByWalletRoleAndWalletStatus(WalletRole walletRole, WalletStatus walletStatus);
    Optional<Wallet> findByMember_MemberIdAndWalletStatus(Long memberId, WalletStatus walletStatus);
    Optional<Wallet> findByWalletAddress(String walletAddress);
}
