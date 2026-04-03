package org.zerock.sto_pr.common.util;

import org.springframework.stereotype.Component;

@Component
public class IdempotencyKeyGenerator {

    public String tradeKey(Long tradeId) {
        return "Trade-" + tradeId;
    }
}
