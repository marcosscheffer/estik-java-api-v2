package com.marcos.estik.auth.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class RefreshToken {
    private UUID id;
    private UUID tokenId;
    private UUID userId;
    private Instant createdAt;
    private Instant expiresAt;
    private Boolean revoked;
    private Instant revokedAt;
    
    public static RefreshToken create(UUID tokenId, UUID userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.id = UUID.randomUUID();
        refreshToken.tokenId = tokenId;
        refreshToken.userId = userId;
        refreshToken.createdAt = Instant.now();
        refreshToken.expiresAt = refreshToken.createdAt.plusSeconds(60 * 60 * 24 * 7); // 7 days
        refreshToken.revoked = false;
        refreshToken.revokedAt = null;
        return refreshToken;
    }

}
