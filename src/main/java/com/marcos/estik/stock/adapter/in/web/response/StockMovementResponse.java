package com.marcos.estik.stock.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.stock.domain.TypeMovementEnum;

public record StockMovementResponse(
    UUID id,
    UUID facilitySource,
    UUID facilityDestination,
    UUID departamentSource,
    UUID departamentDestination,
    UUID itemId,
    String description,
    TypeMovementEnum type,
    Long quantity,
    Instant createdAt
) {
    
}
