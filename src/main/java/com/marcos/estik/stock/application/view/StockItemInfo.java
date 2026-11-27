package com.marcos.estik.stock.application.view;

import java.util.UUID;

public record StockItemInfo(
    UUID id,
    String name,
    String sku
) {
    
}
