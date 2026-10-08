package com.marcos.estik.auth.application.usecase;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.auth.application.port.in.RefreshPort;
import com.marcos.estik.auth.application.port.out.LoadUserByIdPort;
import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.auth.application.result.AuthTokens;
import com.marcos.estik.auth.domain.RefreshToken;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RefreshUseCase implements RefreshPort {
    
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserByIdPort loadUserByIdPort;

    @Override
    public AuthTokens execute(String token) {
        UUID tokenId = tokenProviderPort.getTokenId(token);
        System.out.println(tokenId);
        RefreshToken refreshToken = refreshTokenRepositoryPort.findById(tokenId);
        if (refreshToken.getRevoked() == false 
            && refreshToken.getExpiresAt().isBefore(Instant.now())) {
            refreshToken.setRevoked(true);
            refreshToken.setCreatedAt(Instant.now());
        }

        if (refreshToken.getRevoked()) {
            throw new ExpiredJwtException(null, null, "Token expired");
        }

        return new AuthTokens(
            tokenProviderPort.generateToken(
                loadUserByIdPort.findById(refreshToken.getUserId())
            ),
            token
        );
    }

}