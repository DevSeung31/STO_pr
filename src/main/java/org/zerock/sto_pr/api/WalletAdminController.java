package org.zerock.sto_pr.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.sto_pr.domain.wallet.entity.Wallet;
import org.zerock.sto_pr.domain.wallet.service.WalletAdminSetupService;

@RestController
@RequestMapping("/api/admin/wallets")
public class WalletAdminController {

    private final WalletAdminSetupService walletAdminSetupService;

    public WalletAdminController(WalletAdminSetupService walletAdminSetupService) {
        this.walletAdminSetupService = walletAdminSetupService;
    }

    @PostMapping("/treasury")
    public String createTreasuryWallet() {
        Wallet wallet = walletAdminSetupService.createTreasuryWalletIfAbsent();
        return wallet.getWalletAddress();
    }
}
