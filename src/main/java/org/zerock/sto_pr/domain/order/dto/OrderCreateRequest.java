package org.zerock.sto_pr.domain.order.dto;

import jakarta.validation.constraints.NotNull;
import org.zerock.sto_pr.domain.order.entity.OrderType;

public record OrderCreateRequest(
        @NotNull Long memberId,
        @NotNull Long tokenId,
        @NotNull Long orderPrice,
        @NotNull Long orderQuantity,
        @NotNull OrderType orderType
) {
}
