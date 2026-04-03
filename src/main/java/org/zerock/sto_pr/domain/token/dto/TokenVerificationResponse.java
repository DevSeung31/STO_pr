package org.zerock.sto_pr.domain.token.dto;

public record TokenVerificationResponse(
        Long tokenId,
        boolean nameMatched,
        boolean symbolMathced,
        boolean totalSupplyMathced,
        String contractAddress
) {
}
