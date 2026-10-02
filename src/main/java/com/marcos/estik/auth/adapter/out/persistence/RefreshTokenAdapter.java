package com.marcos.estik.auth.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.domain.RefreshToken;
import com.marcos.estik.user.adapter.out.persistence.JpaUserRepository;
import com.marcos.estik.user.adapter.out.persistence.UserRepositoryAdapter;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class RefreshTokenAdapter implements RefreshTokenRepositoryPort {

    private final JpaRefreshtokenRepository jpaRefreshtokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public Optional<RefreshToken> findById(UUID tokenId) {
        return jpaRefreshtokenRepository.findById(tokenId).map(refreshTokenMapper::toDomain);
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = refreshTokenMapper.toEntity(refreshToken);
        RefreshTokenEntity savedEntity = jpaRefreshtokenRepository.save(entity);
        return refreshTokenMapper.toDomain(savedEntity);
    }
}
