package com.marcos.estik.auth.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.domain.RefreshToken;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class RefreshTokenAdapter implements RefreshTokenRepositoryPort {

    private final JpaRefreshtokenRepository jpaRefreshtokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken findById(UUID tokenId) {
        return refreshTokenMapper.toDomain(jpaRefreshtokenRepository.findById(tokenId)
            .orElseThrow(() -> new EntityNotFoundException("Refresh Token not found")));
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = refreshTokenMapper.toEntity(refreshToken);
        RefreshTokenEntity savedEntity = jpaRefreshtokenRepository.save(entity);
        return refreshTokenMapper.toDomain(savedEntity);
    }
}
