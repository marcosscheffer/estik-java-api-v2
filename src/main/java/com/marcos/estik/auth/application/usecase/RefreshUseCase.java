package com.marcos.estik.auth.application.usecase;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.auth.application.port.in.RefreshPort;
import com.marcos.estik.auth.application.port.out.LoadUserByIdPort;
import com.marcos.estik.auth.application.port.out.LoadUserFindPermissionByRoleIdPort;
import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.auth.application.result.AuthTokens;
import com.marcos.estik.auth.domain.RefreshToken;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.auth.domain.exception.InvalidRefreshTokenException;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshUseCase implements RefreshPort {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserByIdPort loadUserByIdPort;
    private final LoadUserFindPermissionByRoleIdPort loadUserFindPermissionByRoleIdPort;

    @Override
    @Transactional
    public AuthTokens execute(String token) {
        if (!tokenProviderPort.isValidRefreshToken(token)) {
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        UUID tokenId = tokenProviderPort.getTokenId(token);
        RefreshToken refreshToken = refreshTokenRepositoryPort.findById(tokenId);

        if (Boolean.TRUE.equals(refreshToken.getRevoked())
            || refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        UserAuth user = loadUserByIdPort.findById(refreshToken.getUserId());

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        refreshToken.setRevoked(true);
        refreshToken.setRevokedAt(Instant.now());
        refreshTokenRepositoryPort.save(refreshToken);

        RefreshToken newRefreshToken = RefreshToken.create(UUID.randomUUID(), user.getId());
        refreshTokenRepositoryPort.save(newRefreshToken);

        Set<PermissionEnum> permissions = loadUserFindPermissionByRoleIdPort.execute(user.getRoleId());

        return new AuthTokens(
            tokenProviderPort.generateToken(user, permissions),
            tokenProviderPort.generateRefreshToken(user, newRefreshToken.getId())
        );
    }

}
