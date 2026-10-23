package com.marcos.estik.stock.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record StockBalanceResponse (
    UUID id,
    StockItemResponse item,
    StockFacilityResponse facility,
    StockDepartamentResponse departament,
    Long quantity,
    Instant updatedAt
) {

}
