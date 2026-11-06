package com.marcos.estik.access.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Permission {
    UUID id;
    UUID roleId;
    PermissionEnum permission;
    Instant createdAt;
    Instant updatedAt;

    public Permission create(
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

