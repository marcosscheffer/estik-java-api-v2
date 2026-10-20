package com.marcos.estik.item.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ItemResponse(
    UUID id,
    String name,
    String description,
    String code,
    Instant createdAt,
    Instant updatedAt
) {
    
}
