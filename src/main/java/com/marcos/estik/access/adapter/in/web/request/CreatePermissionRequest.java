package com.marcos.estik.access.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public record CreatePermissionRequest(
    UUID roleId,
    PermissionEnum permission
) {
    
}
