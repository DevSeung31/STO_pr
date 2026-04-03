package org.zerock.sto_pr.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
        @Email String email,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String accountPassword
) {
}


//public class MemberSignupRequest {
//
//    private final String email;
//    private final String password;
//    private final String name;
//    private final String accountPassword;
//
//    // 생성자
//    public MemberSignupRequest(String email, String password,
//                               String name, String accountPassword) {
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.accountPassword = accountPassword;
//    }
//
//    // getter 4개
//    public String getEmail() { return email; }
//    public String getPassword() { return password; }
//    public String getName() { return name; }
//    public String getAccountPassword() { return accountPassword; }
//
//    // equals, hashCode, toString
//    @Override
//    public boolean equals(Object o) { ... }
//    @Override
//    public int hashCode() { ... }
//    @Override
//    public String toString() { ... }
//}
