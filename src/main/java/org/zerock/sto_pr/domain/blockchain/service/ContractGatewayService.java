package org.zerock.sto_pr.domain.blockchain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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

    public DeployResult deployResult(
            String tokenName,
            String tokenSymbol,
            Long totalSupply,
            Long decimals,
            String treasuryAddress
    ) throws Exception {

        StoTokenContract contract = StokTokenContract.deploy(
                web3j,
                issuerCredentials,
                gasProvider,
                tokenName,
                tokenSymbol,
                BigInteger.valueOf(totalSupply),
                BigInteger.valueOf(decimals),
                treasuryAddress
        ).send();

        TransactionReceipt receipt = contract.getTransactionReceipt()
                .orElseThrow(() -> new IllegalStateException("배포 영수증 없음"));

        return new DeployResult(contract.getContractAddress(), receipt.getTransactionHash());
    }

    public TradeRecordResult recordTrade(
            String contractAddress,
            Long tradeId,
            String sellerAddress,
            String buyerAddress,
            Long quantity,
            Long price
    ) throws Exception {
        StoTokenContract contract = StoTokenContract.load(
                contractAddress,
                web3j,
                issuerCredentials,
                gasProvider
        );

        TransactionReceipt receipt = contract.recordTrade(
                BigInteger.valueOf(tradeId),
                sellerAddress,
                buyerAddress,
                BigInteger.valueOf(quantity),
                BigInteger.valueOf(price)
        ).send();

        return new TradeRecordResult(
                receipt.getTransactionHash(),
                receipt.getGasUsed().longValue(),
                receipt.getBlockNumber().longValue()
        );
    }

    public ChainTokenInfo loadTokenInfo(String contractAddress) throws Exception {
        StoTokenContract contract = StoTokenContract.load(
                contractAddress,
                web3j,
                issuerCredentials,
                gasProvider
        );

        String name = contract.name().send();
        String symbol = contract.symbol().send();
        BigInteger totalSupply = contract.totalSupply().send();

        return new ChainTokenInfo(name, symbol, totalSupply.longValue());
    }


   public record DeployResult(String contractAddress, String txHash) {}
    public record TradeRecordResult(String txHash, Long gasUsed, Long blockNumber) {}
    public record ChainTokenInfo(String name, String symbol, Long totalSupply) {}
}
