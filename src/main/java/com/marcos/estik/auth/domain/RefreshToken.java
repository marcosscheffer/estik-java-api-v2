package com.marcos.estik.auth.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class RefreshToken {
    private UUID id;
    private UUID tokenId;
    private UUID userId;
    private Instant createdAt;
    private Instant expiresAt;
    private Boolean revoked;
    private Instant revokedAt;
    
    public static RefreshToken create(UUID tokenId, UUID userId) {
        Instant createdAt = Instant.now();
        return new RefreshToken(
            UUID.randomUUID(),
            tokenId,
            userId,
            createdAt,
            createdAt.plusSeconds(60 * 60 * 24 * 7),
            Boolean.FALSE,
            null
        );
    }
}
