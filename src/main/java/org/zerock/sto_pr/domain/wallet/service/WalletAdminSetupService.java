package org.zerock.sto_pr.domain.wallet.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.zerock.sto_pr.domain.wallet.entity.Wallet;
import org.zerock.sto_pr.domain.wallet.entity.WalletRole;
import org.zerock.sto_pr.domain.wallet.entity.WalletStatus;
import org.zerock.sto_pr.domain.wallet.entity.WalletType;
import org.zerock.sto_pr.domain.wallet.repository.WalletRepository;

@Service
public class WalletAdminSetupService {

    private final WalletRepository walletRepository;
    private final WalletGenerationService walletGenerationService;

    public WalletAdminSetupService(WalletRepository walletRepository, WalletGenerationService walletGenerationService) {
        this.walletRepository = walletRepository;
        this.walletGenerationService = walletGenerationService;
    }

    @Transactional
    public void createIssuerWalletIfAbsent() {
        walletRepository.findByWalletRoleAndWalletStatus(WalletRole.ISSUER, WalletStatus.ACTIVE)
                .orElseGet(() -> {
                   WalletGenerationService.GeneratedWallet generated = walletGenerationService.generate();
                   Wallet wallet = new Wallet(
                           null,
                           generated.address(),
                           WalletType.CUSTODIAL,
                           WalletStatus.ACTIVE,
                           WalletRole.ISSUER,
                           "토큰 발행 지갑",
                           generated.encryptedPrivateKey(),
                           generated.keyVersion(),
                           "sepolia"
                   );
                   return walletRepository.save(wallet);
                });
    }

    @Transactional
    public void createTreasuryWalletIfAbsent() {
        walletRepository.findByWalletRoleAndWalletStatus(WalletRole.PLATFORM_TREASURY, WalletStatus.ACTIVE)
                .orElseGet(() -> {
                    WalletGenerationService.GeneratedWallet generated = walletGenerationService.generate();
                    Wallet wallet = new Wallet(
                            null,
                            generated.address(),
                            WalletType.CUSTODIAL,
                            WalletStatus.ACTIVE,
                            WalletRole.PLATFORM_TREASURY,
                            "플랫폼 재고 지갑",
                            generated.encryptedPrivateKey(),
                            generated.keyVersion(),
                            "sepolia"
                    );
                    return walletRepository.save(wallet);
                });
    }
}
