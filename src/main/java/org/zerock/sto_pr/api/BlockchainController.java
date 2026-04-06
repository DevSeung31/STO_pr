package org.zerock.sto_pr.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.sto_pr.domain.blockchain.service.BlockchainWorkerService;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    private final BlockchainWorkerService blockchainWorkerService;

    public BlockchainController(BlockchainWorkerService blockchainWorkerService) {
        this.blockchainWorkerService = blockchainWorkerService;
    }

    @PostMapping("/process")
    public String process() {
        blockchainWorkerService.processPending();
        return "ok";
    }
}
