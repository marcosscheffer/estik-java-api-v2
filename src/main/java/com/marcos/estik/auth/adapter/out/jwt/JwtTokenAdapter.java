package com.marcos.estik.auth.adapter.out.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.domain.RoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenAdapter implements TokenProviderPort {

    private SecretKey secret;
    private final String issuer;
    private final Duration expirationTime;
    private final Duration refreshTokenExpirationTime;

    public JwtTokenAdapter(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.issuer}") String issuer,
        @Value("${jwt.expiration-time}") Duration expirationTime,
        @Value("${jwt.refresh-token-expiration-time}") Duration refreshTokenExpirationTime
    ) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.expirationTime = expirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    @Override
    public String generateToken(UserAuth user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationTime)))

                .claim("type", "access")
                .claim("username", user.getUsername())
                .claim("role", user.getRole().name())

                .signWith(secret)
                .compact();
    }

    @Override
    public String generateRefreshToken(UserAuth user, UUID tokenId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .id(tokenId.toString())
                .subject(user.getId().toString())
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(refreshTokenExpirationTime)))

                .claim("type", "refresh")

                .signWith(secret)
                .compact();
    }

    @Override
    public UUID getUserId(String token) {
        Claims claim = getClaims(token);
        return UUID.fromString(claim.getSubject());
    }

    @Override
    public UUID getTokenId(String token) {
        Claims claim = getClaims(token);
        return UUID.fromString(claim.getId());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(secret)
            .requireIssuer(issuer)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    @Override
    public Boolean isValidRefreshToken(String token) {
        try {
            Claims claims = getClaims(token);
            String type = claims.get("type", String.class);
            return "refresh".equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean isValidAccessToken(String token) {
        try {
            Claims claims = getClaims(token);
            String type = claims.get("type", String.class);
            return "access".equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public RoleEnum getRole(String token) {
        Claims claims = getClaims(token);
        String role = claims.get("role", String.class);
        return RoleEnum.valueOf(role);
    }

    @Override
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username", String.class);
    }
}
