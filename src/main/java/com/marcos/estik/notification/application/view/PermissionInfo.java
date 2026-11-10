package com.marcos.estik.notification.application.view;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public record PermissionInfo(
    UUID id,
    UUID roleId,
    PermissionEnum permission
) {
    
}
