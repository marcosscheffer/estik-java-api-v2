package com.marcos.estik.user.adapter.in.web.response;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.RoleEnum;


public record UserResponseSummary(
    UUID id,
    String username,
    String email,
    RoleEnum role,
    Boolean active
) {
    
}
