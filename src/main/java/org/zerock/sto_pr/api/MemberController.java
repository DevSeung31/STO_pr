package org.zerock.sto_pr.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.sto_pr.domain.member.dto.MemberSignupRequest;
import org.zerock.sto_pr.domain.member.dto.MemberSignupResponse;
import org.zerock.sto_pr.domain.member.service.MemberSignupService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberSignupService memberSignupService;


    public MemberController(MemberSignupService memberSignupService) {
        this.memberSignupService = memberSignupService;
    }

    @PostMapping("/signup")
    public MemberSignupResponse signup(@Valid @RequestBody MemberSignupRequest request) {
        return memberSignupService.signup(request);
    }
}
