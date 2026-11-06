package com.marcos.estik.access.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Permission {
    private UUID id;
    private UUID roleId;
    private PermissionEnum permission;
    private Instant createdAt;
    private Instant updatedAt;

    public static Permission create(
        UUID roleId,
        PermissionEnum permission
    ) {
        return new Permission(
            UUID.randomUUID(), 
            roleId, 
            permission, 
            Instant.now(), 
            Instant.now()
        );
    }
}

