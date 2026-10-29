package com.marcos.estik.user.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    
    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);
}
