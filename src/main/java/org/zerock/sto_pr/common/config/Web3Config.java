package org.zerock.sto_pr.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {

    @Bean
    public Web3j web3j(@Value("${blockchain.rpc-url}") String rpcUrl) {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public Credentials issuerCredentials (
            @Value("${blockchain.issuer-private-key}") String privateKey
    ) {
        return Credentials.create(privateKey);
    }
}
