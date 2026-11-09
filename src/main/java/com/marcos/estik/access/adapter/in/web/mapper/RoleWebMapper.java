package com.marcos.estik.access.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.access.adapter.in.web.response.RoleResponse;
import com.marcos.estik.access.domain.Role;



@Mapper(componentModel = "spring")
public interface RoleWebMapper {

    RoleResponse toResponse(Role role);
    
}
