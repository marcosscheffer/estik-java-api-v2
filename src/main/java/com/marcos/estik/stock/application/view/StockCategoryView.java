package com.marcos.estik.stock.application.view;

import java.util.UUID;

public record StockCategoryView(
    UUID id,
    String name,
    String description,
    Long minimum,
    Long quantity
) {
    
}
