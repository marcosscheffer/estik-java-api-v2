package com.marcos.estik.stock.adapter.in.web.response;

import java.util.UUID;

public record StockFacilityResponse(
    UUID id,
    String name,
    String code
) {
    
}
