package com.marcos.estik.stock.adapter.in.web.response;

import java.util.UUID;

public record StockBalanceSummaryResponse(
    UUID id,
    StockItemResponse item,
    StockFacilityResponse facility,
    Long quantity
) {

}
