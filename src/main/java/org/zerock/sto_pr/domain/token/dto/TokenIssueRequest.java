package org.zerock.sto_pr.domain.token.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenIssueRequest(
        @NotNull Long assetId,
        @NotBlank String tokenName,
        @NotBlank String tokenSymbol,
        @NotNull Long totalSupply,
        @NotNull Long decimals,
        @NotNull Long initPrice
) {
}
