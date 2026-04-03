package org.zerock.sto_pr.common.crypto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class WalletCryptoService {

    private final SecretKeySpec keySpec;

    public WalletCryptoService(@Value("${wallet.crypto.secret}") String secret) {
        byte[] key = secret.getBytes(StandardCharsets.UTF_8);
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("AES 키 길이는 16/24/32 바이트여야 합니다.");
        }

        this.keySpec = new SecretKeySpec(key,"AES");
    }

    public String encrypt(String plain) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("개인키 암호화 실패", e);
        }
    }

    public String decrypt(String encrypted) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = Base64.getDecoder().decode(encrypted);
            return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("개인키 복호화 실패",e);
        }
    }
}
