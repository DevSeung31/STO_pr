package org.zerock.sto_pr.domain.wallet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Wallet createIssuerWalletIfAbsent() {
        return walletRepository.findByWalletRoleAndWalletStatus(WalletRole.ISSUER, WalletStatus.ACTIVE)
                .orElseGet(() -> {
                   WalletGenerationService.GeneratedWallet generated = walletGenerationService.generate();
                   Wallet wallet = new Wallet(
                           null,
                           generated.address(),
                           WalletType.CUSTODIAL,
                           WalletStatus.ACTIVE,
                           WalletRole.ISSUER,
                           generated.encryptedPrivateKey()
                   );
                   return walletRepository.save(wallet);
                });
    }

    @Transactional
    public Wallet createTreasuryWalletIfAbsent() {
        return walletRepository.findByWalletRoleAndWalletStatus(WalletRole.PLATFORM_TREASURY, WalletStatus.ACTIVE)
                .orElseGet(() -> {
                    WalletGenerationService.GeneratedWallet generated = walletGenerationService.generate();
                    Wallet wallet = new Wallet(
                            null,
                            generated.address(),
                            WalletType.CUSTODIAL,
                            WalletStatus.ACTIVE,
                            WalletRole.PLATFORM_TREASURY,
                            generated.encryptedPrivateKey()
                    );
                    return walletRepository.save(wallet);
                });
    }
}
