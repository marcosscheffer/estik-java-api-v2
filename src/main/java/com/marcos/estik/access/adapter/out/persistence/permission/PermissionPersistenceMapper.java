package com.marcos.estik.access.adapter.out.persistence.permission;

import org.mapstruct.Mapper;

import com.marcos.estik.access.domain.Permission;

@Mapper(componentModel = "spring")
public interface PermissionPersistenceMapper {

    PermissionEntity toEntity(Permission permission);

    Permission toDomain(PermissionEntity permission);
    
}
