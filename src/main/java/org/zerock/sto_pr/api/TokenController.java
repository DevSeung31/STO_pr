package org.zerock.sto_pr.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.zerock.sto_pr.domain.token.dto.TokenIssueRequest;
import org.zerock.sto_pr.domain.token.dto.TokenVerificationResponse;
import org.zerock.sto_pr.domain.token.service.TokenIssueService;
import org.zerock.sto_pr.domain.token.service.TokenVerificationService;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenIssueService tokenIssueService;
    private final TokenVerificationService tokenVerificationService;

    public TokenController(TokenIssueService tokenIssueService, TokenVerificationService tokenVerificationService) {
        this.tokenIssueService = tokenIssueService;
        this.tokenVerificationService = tokenVerificationService;
    }

    @PostMapping("/issue")
    public Long issue(@Valid @RequestBody TokenIssueRequest request) throws Exception {
        return tokenIssueService.issue(request);
    }

    @GetMapping("/{tokenId}/verify")
    public TokenVerificationResponse verify(@PathVariable Long tokenId) throws Exception {
        return tokenVerificationService.verify(tokenId);
    }
}
