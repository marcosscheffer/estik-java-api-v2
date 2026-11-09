package com.marcos.estik.access.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public record PermissionResponse (
    UUID id,
    UUID roleId,
    PermissionEnum permission,
    Instant createdAt,
    Instant updatedAt
) {
    
}
