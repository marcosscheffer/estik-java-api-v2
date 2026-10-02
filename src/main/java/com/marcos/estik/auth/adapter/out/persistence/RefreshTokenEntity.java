package com.marcos.estik.auth.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name="refresh_tokens")
@Getter 
@Setter 
public class RefreshTokenEntity {
    @Id 
    private UUID id;

    @Column(name = "token_id")
    private UUID tokenId;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "expires_at")
    private Instant expiresAt;
    private Boolean revoked;
    @Column(name = "revoked_at")
    private Instant revokedAt;
}
