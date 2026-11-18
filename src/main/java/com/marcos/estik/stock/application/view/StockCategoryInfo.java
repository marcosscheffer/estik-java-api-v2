package com.marcos.estik.stock.application.view;

import java.util.UUID;

public record StockCategoryInfo(
    UUID id,
    String name,
    Long minimum,
    String description
) {
    
}
