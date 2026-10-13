package com.marcos.estik.security;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.RoleEnum;

public record AuthenticatedUser(
    UUID id,
    String username, 
    RoleEnum role
) {
}
