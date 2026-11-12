package com.marcos.estik.user.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String username,
    String email,
    UUID facilityId,
    UserSummaryRole role,
    UserSpecializationResponse specialization,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
