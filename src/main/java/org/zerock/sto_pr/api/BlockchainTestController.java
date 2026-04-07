package org.zerock.sto_pr.api;

import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;
import org.zerock.sto_pr.domain.blockchain.service.BlockchainWorkerService;
import org.zerock.sto_pr.domain.blockchain.service.ContractGatewayService;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/blockchain/test")
public class BlockchainTestController {

    private final ContractGatewayService contractGatewayService;
    private final BlockchainWorkerService blockchainTxWorkerService;
    private final Web3j web3j;
    private final Credentials credentials;

    public BlockchainTestController(ContractGatewayService contractGatewayService, BlockchainWorkerService blockchainTxWorkerService, Web3j web3j, Credentials credentials) {
        this.contractGatewayService = contractGatewayService;
        this.blockchainTxWorkerService = blockchainTxWorkerService;
        this.web3j = web3j;
        this.credentials = credentials;
    }


    @PostMapping("/record-trade")
    public ContractGatewayService.TradeRecordResult recordTrade() throws Exception {
        String contractAddress = "";

        return contractGatewayService.recordTrade(
                contractAddress,
                1L,
                "0x1111111111111111111111111111111111111111",
                "0x2222222222222222222222222222222222222222",
                10L,
                5000L
        );
    }

    @PostMapping("/process-pending")
    public String processPending() {
        blockchainTxWorkerService.processPending();
        return "pending 큐 처리 완료";
    }

    @GetMapping("/client-version")
    public String clientVersion() throws Exception {
        return web3j.web3ClientVersion().send().getWeb3ClientVersion();
    }

    @GetMapping("/nonce")
    public Map<String, Object> getNonce(@RequestParam String address) throws Exception {
        EthGetTransactionCount latest = web3j.ethGetTransactionCount(
                address,
                org.web3j.protocol.core.DefaultBlockParameterName.LATEST
        ).send();

        EthGetTransactionCount pending = web3j.ethGetTransactionCount(
                address,
                org.web3j.protocol.core.DefaultBlockParameterName.PENDING
        ).send();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("address", address);
        result.put("latestNonce", latest.getTransactionCount());
        result.put("pendingNonce", pending.getTransactionCount());

        return result;
    }

    @GetMapping("/my-address")
    public String myAddress() {
        return credentials.getAddress();
    }

    @PostMapping("/fix")
    public Map<String, Object> fix(@RequestParam int nonce) throws Exception {

        BigInteger nonceValue = BigInteger.valueOf(nonce);
        String myAddress = credentials.getAddress();

        RawTransaction tx = RawTransaction.createEtherTransaction(
                nonceValue,
                BigInteger.valueOf(500_000_000_000L),  // 500 gwei (legacy)
                BigInteger.valueOf(21000),
                myAddress,
                BigInteger.ZERO
        );http://localhost:8081/api/blockchain/test/nonce?address=0xf1afe11089ee1720a6ae931c68434f95a4f28ee8


        byte[] signed = TransactionEncoder.signMessage(tx, 11155111L, credentials);
        String hex = Numeric.toHexString(signed);

        EthSendTransaction res = web3j.ethSendRawTransaction(hex).send();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("address", myAddress);
        result.put("nonce", nonce);

        if (res.hasError()) {
            result.put("success", false);
            result.put("errorCode", res.getError().getCode());
            result.put("errorMessage", res.getError().getMessage());
        } else {
            result.put("success", true);
            result.put("txHash", res.getTransactionHash());
        }

        return result;
    }
}