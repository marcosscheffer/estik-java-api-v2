package com.marcos.estik.stock.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.stock.domain.TypeMovementEnum;

public record StockMovementResponse(
    UUID id,
    StockFacilityResponse facilitySource,
    StockFacilityResponse facilityDestination,
    StockDepartamentResponse departamentSource,
    StockDepartamentResponse departamentDestination,
    StockItemResponse item,
    String description,
    TypeMovementEnum type,
    Long quantity,
    Instant createdAt
) {
    
}
