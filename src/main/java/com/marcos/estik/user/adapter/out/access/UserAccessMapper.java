package com.marcos.estik.user.adapter.out.access;


import org.mapstruct.Mapper;

import com.marcos.estik.access.domain.Role;
import com.marcos.estik.user.application.view.UserRoleInfo;

@Mapper(componentModel = "spring")
public interface UserAccessMapper {

    UserRoleInfo toInfo(Role role);
    
}
