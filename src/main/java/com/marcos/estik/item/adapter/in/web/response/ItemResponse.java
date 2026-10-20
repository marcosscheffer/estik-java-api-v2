package com.marcos.estik.item.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ItemResponse(
    UUID id,
    String name,
    String description,
    String sku,
    Long minimum,
    UUID categoryId,
    Instant createdAt,
    Instant updatedAt
) {
    
}
