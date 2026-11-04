package com.marcos.estik.user.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.RoleEnum;

public record UserResponse(
    UUID id,
    String username,
    String email,
    UUID facilityId,
    RoleEnum role,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
