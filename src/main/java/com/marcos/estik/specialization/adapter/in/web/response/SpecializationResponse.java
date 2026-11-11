package com.marcos.estik.specialization.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record SpecializationResponse(
    UUID id,
    String name,
    Instant createdAt
) {
    
}
