package com.marcos.estik.access.adapter.out.persistence.role;

import org.mapstruct.Mapper;

import com.marcos.estik.access.domain.Role;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {

    RoleEntity toEntity(Role role);

    Role toDomain(RoleEntity role);
    
}
