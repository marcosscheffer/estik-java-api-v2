package com.marcos.estik.notification.adapter.out.permission;

import org.mapstruct.Mapper;

import com.marcos.estik.access.domain.Permission;
import com.marcos.estik.notification.application.view.NotificationPermissionInfo;

@Mapper(componentModel = "spring")
public interface NotificationPermissionMapper {

    NotificationPermissionInfo toInfo(Permission permission);
    
}
