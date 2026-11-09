package com.marcos.estik.security;

import java.util.Set;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

public record AuthenticatedUser(
    UUID id,
    String username, 
    Set<PermissionEnum> permissions
) {

    public boolean has(PermissionEnum permission) {
        return permissions.contains(permission);
    }
}
