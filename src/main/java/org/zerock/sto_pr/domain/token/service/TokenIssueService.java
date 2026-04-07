package org.zerock.sto_pr.domain.token.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.admin.entity.Admin;
import org.zerock.sto_pr.domain.admin.repository.AdminRepository;
import org.zerock.sto_pr.domain.asset.entity.Asset;
import org.zerock.sto_pr.domain.asset.repository.AssetRepository;
import org.zerock.sto_pr.domain.blockchain.service.BlockchainTxSaveService;
import org.zerock.sto_pr.domain.blockchain.service.ContractGatewayService;
import org.zerock.sto_pr.domain.token.dto.TokenIssueRequest;
import org.zerock.sto_pr.domain.token.entity.PlatformTokenHolding;
import org.zerock.sto_pr.domain.token.entity.Token;
import org.zerock.sto_pr.domain.token.entity.TokenStatus;
import org.zerock.sto_pr.domain.token.repository.PlatformTokenHoldingRepository;
import org.zerock.sto_pr.domain.token.repository.TokenRepository;
import org.zerock.sto_pr.domain.wallet.entity.Wallet;
import org.zerock.sto_pr.domain.wallet.entity.WalletRole;
import org.zerock.sto_pr.domain.wallet.entity.WalletStatus;
import org.zerock.sto_pr.domain.wallet.repository.WalletRepository;

import java.math.BigDecimal;

@Service
public class TokenIssueService {

    private final AssetRepository assetRepository;
    private final WalletRepository walletRepository;
    private final TokenRepository tokenRepository;
    private final PlatformTokenHoldingRepository platformTokenHoldingRepository;
    private final AdminRepository adminRepository;
    private final ContractGatewayService contractGatewayService;
    private final BlockchainTxSaveService blockchainTxSaveService;


    public TokenIssueService(AssetRepository assetRepository, WalletRepository walletRepository, TokenRepository tokenRepository, PlatformTokenHoldingRepository platformTokenHoldingRepository, AdminRepository adminRepository, ContractGatewayService contractGatewayService, BlockchainTxSaveService blockchainTxSaveService) {
        this.assetRepository = assetRepository;
        this.walletRepository = walletRepository;
        this.tokenRepository = tokenRepository;
        this.platformTokenHoldingRepository = platformTokenHoldingRepository;
        this.adminRepository = adminRepository;
        this.contractGatewayService = contractGatewayService;
        this.blockchainTxSaveService = blockchainTxSaveService;
    }

    @Transactional
    public Long issue(TokenIssueRequest request) throws Exception {
        Asset asset = assetRepository.findById(request.assetId())
                .orElseThrow(() -> new IllegalArgumentException("asset 없음"));

        Wallet treasuryWallet = walletRepository
                .findByWalletRoleAndWalletStatus(WalletRole.PLATFORM_TREASURY, WalletStatus.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("플랫폼 treasury 지갑없음"));

        ContractGatewayService.DeployResult deployResult = contractGatewayService.deployResult(
                request.tokenName(),
                request.tokenSymbol(),
                request.totalSupply(),
                request.decimals(),
                treasuryWallet.getWalletAddress()
        );

        Token token = new Token(
                asset,
                request.totalSupply(),
                request.totalSupply(),
                request.tokenName(),
                request.tokenSymbol(),
                deployResult.contractAddress(),
                request.decimals(),
                request.initPrice(),
                BigDecimal.valueOf(request.initPrice()),
                TokenStatus.ISSUED
        );
        tokenRepository.save(token);

        Admin admin = adminRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("admin 없음"));

        PlatformTokenHolding platformTokenHolding =
                new PlatformTokenHolding(admin, token, request.totalSupply(), request.initPrice());
        platformTokenHoldingRepository.save(platformTokenHolding);
        blockchainTxSaveService.saveDeployTx(platformTokenHolding, deployResult);
        return token.getTokenId();
    }
}
