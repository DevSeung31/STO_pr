package org.zerock.sto_pr.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.sto_pr.domain.blockchain.service.ContractGatewayService;

@RestController
@RequestMapping("/api/blockchain/test")
public class BlockchainTestController {

    private final ContractGatewayService contractGatewayService;

    public BlockchainTestController(ContractGatewayService contractGatewayService) {
        this.contractGatewayService = contractGatewayService;
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
}