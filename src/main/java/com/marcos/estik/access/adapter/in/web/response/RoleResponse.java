package com.marcos.estik.access.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record RoleResponse(
    UUID id,
    String name,
    Instant createdAt,
    Instant updatedAt
) {
    
}
