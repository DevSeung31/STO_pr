package org.zerock.sto_pr.domain.wallet.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.zerock.sto_pr.common.crypto.WalletCryptoService;

@Service
public class WalletGenerationService {

    private final WalletCryptoService walletCryptoService;


    public WalletGenerationService(WalletCryptoService walletCryptoService) {
        this.walletCryptoService = walletCryptoService;
    }

    public GeneratedWallet generate() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String privateKey = keyPair.getPrivateKey().toString(16);
            Credentials credentials = Credentials.create(privateKey);

            String address = credentials.getAddress();
            String encryptedPrivateKey = walletCryptoService.encrypt(privateKey);

            return new GeneratedWallet(address, encryptedPrivateKey, "v1");
        } catch (Exception e) {
            throw new IllegalStateException("지갑 생성 실패", e);
        }
    }

    public record GeneratedWallet(
            String address,
            String encryptedPrivateKey,
            String keyVersion
    ){}
}
