package com.marcos.estik.stock.adapter.in.web.response;

import java.util.UUID;

public record StockCategoryResponse(
    UUID id,
    String name,
    String description,
    Long minimum,
    Long quantity
) {
    
}
