package org.zerock.sto_pr.domain.token.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.domain.blockchain.service.ContractGatewayService;
import org.zerock.sto_pr.domain.token.dto.TokenVerificationResponse;
import org.zerock.sto_pr.domain.token.entity.Token;
import org.zerock.sto_pr.domain.token.repository.TokenRepository;

@Service
public class TokenVerificationService {

    private final TokenRepository tokenRepository;
    private final ContractGatewayService contractGatewayService;

    public TokenVerificationService(TokenRepository tokenRepository, ContractGatewayService contractGatewayService) {
        this.tokenRepository = tokenRepository;
        this.contractGatewayService = contractGatewayService;
    }

    @Transactional(readOnly = true)
    public TokenVerificationResponse verify(Long tokenId) throws Exception {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new IllegalArgumentException("token 없음"));

        ContractGatewayService.chainTokenInfo info =
                contractGatewayService.loadTokenInfo(token.getContractAddress());

        boolean nameMathced = token.getTokenName().equals(info.name());
        boolean symbolMatched = token.getTokenSymbol().equals(info.symbol());
        boolean totalSupplyMathced = token.getTotalSupply().equals(info.totalSupply());

        return new TokenVerificationResponse(
                tokenId,
                nameMathced,
                symbolMatched,
                totalSupplyMathced,
                token.getContractAddress()
        );

    }
}
