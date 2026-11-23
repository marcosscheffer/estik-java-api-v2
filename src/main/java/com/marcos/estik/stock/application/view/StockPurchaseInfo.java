package com.marcos.estik.stock.application.view;

import java.util.UUID;

public record StockPurchaseInfo(
    UUID id,
    String code,
    UUID fileId
) {
    
}
