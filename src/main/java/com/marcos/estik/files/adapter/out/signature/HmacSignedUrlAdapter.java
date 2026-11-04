package com.marcos.estik.files.adapter.out.signature;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.out.SignedUrlPort;

@Component 
public class HmacSignedUrlAdapter implements SignedUrlPort {

    private static final String ALGORITHM = "HmacSHA256";

    private final SecretKeySpec key;
    private final Duration ttl;

    public HmacSignedUrlAdapter(
        @Value("${storage.signing-secret}") String secret,
        @Value("${storage.signed-url.ttl}") Duration ttl
    ) {
        this.key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        this.ttl = ttl;
    }
    
    @Override
    public String sign(UUID fileId) {
        Long expires = Instant.now().plus(ttl).getEpochSecond();
        String signature = hmac(fileId + ":" + expires);
        return "/files/" + fileId + "?expires=" + expires + "&signature=" + signature;
    }

    @Override
    public Boolean isValid(UUID fileId, Long expires, String signature) {
        if(Instant.now().getEpochSecond() > expires) {
            return false;
        }

        String expected = hmac(fileId + ":" + expires);

        return MessageDigest.isEqual(
            expected.getBytes(StandardCharsets.UTF_8),
            signature.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String hmac(String data) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(key);
            byte[] raw = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(raw);
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            throw new IllegalStateException("Failed to sign url", exception);
        }
    }
}
