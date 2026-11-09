package com.marcos.estik.access.adapter.in.web.mapper;


import org.mapstruct.Mapper;

import com.marcos.estik.access.adapter.in.web.response.PermissionResponse;
import com.marcos.estik.access.domain.Permission;

@Mapper(componentModel = "spring")
public interface PermissionWebMapper {

    PermissionResponse toResponse(Permission permission);
    
}
