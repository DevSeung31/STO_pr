package org.zerock.sto_pr.common.util;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

    public String generate() {
        return "110-" + System.currentTimeMillis();
    }
}
