package com.marcos.estik.user.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;

public record UserResponse(
    UUID id,
    String username,
    String email,
    UUID facility,
    RoleEnum role,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
