package org.zerock.sto_pr.domain.blockchain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Service
public class ContractGatewayService {

    private final Web3j web3j;
    private final Credentials issuerCredentials;
    private final StaticGasProvider gasProvider;


    public ContractGatewayService(Web3j web3j, Credentials issuerCredentials, @Value("${blockchain.contract-gas-price-wei}") long gasPriceWei, @Value("{blockchain.contract-gas-limit}") long gasLimit) {
        this.web3j = web3j;
        this.issuerCredentials = issuerCredentials;
        this.gasProvider = new StaticGasProvider(
                BigInteger.valueOf(gasPriceWei),
                BigInteger.valueOf(gasLimit)
        );
    }
}
