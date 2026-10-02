package com.marcos.estik.security;

import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;

public record AuthenticatedUser(
    UUID id,
    String username, 
    RoleEnum role
) {
}
