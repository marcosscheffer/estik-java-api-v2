package com.marcos.estik.auth.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.marcos.estik.auth.domain.RefreshToken;

public interface RefreshTokenRepositoryPort {
    Optional<RefreshToken> findById(UUID tokenId);

    RefreshToken save(RefreshToken refreshToken);

}
