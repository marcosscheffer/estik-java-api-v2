package com.marcos.estik.auth.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record UserAuthResponse(
    UUID id,
    String username,
    String email,
    UUID facilityId,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
