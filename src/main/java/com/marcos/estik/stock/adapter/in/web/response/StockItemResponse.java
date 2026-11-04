package com.marcos.estik.stock.adapter.in.web.response;

import java.util.UUID;

public record StockItemResponse(
    UUID id,
    String name,
    String sku,
    Long minimum
) {
    
}
