package org.zerock.sto_pr.domain.member.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sto_pr.common.util.AccountNumberGenerator;
import org.zerock.sto_pr.domain.account.entity.Account;
import org.zerock.sto_pr.domain.account.repository.AccountRepository;
import org.zerock.sto_pr.domain.member.dto.MemberSignupRequest;
import org.zerock.sto_pr.domain.member.dto.MemberSignupResponse;
import org.zerock.sto_pr.domain.member.entity.Member;
import org.zerock.sto_pr.domain.member.repository.MemberRepository;
import org.zerock.sto_pr.domain.wallet.entity.Wallet;
import org.zerock.sto_pr.domain.wallet.entity.WalletRole;
import org.zerock.sto_pr.domain.wallet.entity.WalletStatus;
import org.zerock.sto_pr.domain.wallet.entity.WalletType;
import org.zerock.sto_pr.domain.wallet.repository.WalletRepository;
import org.zerock.sto_pr.domain.wallet.service.WalletGenerationService;

@Service
public class MemberSignupService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountNumberGenerator accountNumberGenerator;
    private final WalletGenerationService walletGenerationService;


    public MemberSignupService(MemberRepository memberRepository, AccountRepository accountRepository, WalletRepository walletRepository, PasswordEncoder passwordEncoder, AccountNumberGenerator accountNumberGenerator, WalletGenerationService walletGenerationService) {
        this.memberRepository = memberRepository;
        this.accountRepository = accountRepository;
        this.walletRepository = walletRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountNumberGenerator = accountNumberGenerator;
        this.walletGenerationService = walletGenerationService;
    }

    @Transactional
    public MemberSignupResponse signup(MemberSignupRequest request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }

        Member member = new Member(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );

        memberRepository.save(member);

        Account account = new Account(
                member,
                passwordEncoder.encode(request.accountPassword()),
                accountNumberGenerator.generate()
        );
        accountRepository.save(account);

        WalletGenerationService.GeneratedWallet generatedWallet = walletGenerationService.generate();

        Wallet wallet = new Wallet(
                member,
                generatedWallet.address(),
                WalletType.CUSTODIAL,
                WalletStatus.ACTIVE,
                WalletRole.MEMBER,
                generatedWallet.encryptedPrivateKey()
        );

        walletRepository.save(wallet);

        return new MemberSignupResponse(
                member.getMemberId(),
                wallet.getWalletAddress(),
                account.getAccountNumber()
        );
    }
}
