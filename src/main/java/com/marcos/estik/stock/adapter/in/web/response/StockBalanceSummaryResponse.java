package com.marcos.estik.stock.adapter.in.web.response;

import java.util.UUID;

public record StockBalanceSummaryResponse(
    UUID id,
    UUID itemId,
    UUID facilityId,
    Long quantity
) {

}
