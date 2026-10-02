package com.marcos.estik.auth.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.auth.domain.RefreshToken;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {
    RefreshToken toDomain(RefreshTokenEntity entity);

    RefreshTokenEntity toEntity(RefreshToken refreshToken);
}
