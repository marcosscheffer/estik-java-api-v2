package com.marcos.estik.auth.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.user.adapter.out.persistence.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity userId;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "expires_at")
    private Instant expiresAt;
    private Boolean revoked;
    @Column(name = "revoked_at")
    private Instant revokedAt;
}
