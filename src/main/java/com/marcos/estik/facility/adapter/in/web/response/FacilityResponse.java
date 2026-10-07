package com.marcos.estik.facility.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record FacilityResponse(
    UUID id,
    String name,
    String code,
    Instant createdAt,
    Instant updatedAt
) {
    
}
