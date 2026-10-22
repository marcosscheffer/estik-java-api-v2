package com.marcos.estik.stock.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.stock.domain.TypeMovementEnum;

public record StockMovementSummaryResponse(
    UUID id,
    TypeMovementEnum type,
    UUID facilitySource,
    UUID facilityDestination,
    UUID itemId,
    Long quantity,
    Instant createdAt
) {
    
}
