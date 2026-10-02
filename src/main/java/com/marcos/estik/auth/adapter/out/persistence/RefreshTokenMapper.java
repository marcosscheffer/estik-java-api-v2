package com.marcos.estik.auth.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.auth.domain.RefreshToken;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {
    RefreshToken toDomain(RefreshTokenEntity entity);

    @Mapping(target = "userId", ignore = true)
    RefreshTokenEntity toEntity(RefreshToken refreshToken);
}
