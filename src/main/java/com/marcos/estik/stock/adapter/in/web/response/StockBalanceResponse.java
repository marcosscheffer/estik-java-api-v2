package com.marcos.estik.stock.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record StockBalanceResponse (
    UUID id,
    UUID itemId,
    UUID facilityId,
    UUID departamentId,
    Long quantity,
    Instant updatedAt
) {

}
