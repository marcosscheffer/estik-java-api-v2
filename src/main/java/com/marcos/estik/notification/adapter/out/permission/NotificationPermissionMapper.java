package com.marcos.estik.notification.adapter.out.permission;

import org.mapstruct.Mapper;

import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.notification.application.view.PermissionInfo;

@Mapper(componentModel = "spring")
public interface NotificationPermissionMapper {

    PermissionInfo toInfo(Permission permission);
    
}
