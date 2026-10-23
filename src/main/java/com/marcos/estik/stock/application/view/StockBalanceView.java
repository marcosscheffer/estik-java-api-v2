package com.marcos.estik.stock.application.view;

import com.marcos.estik.stock.domain.StockBalance;

public record StockBalanceView(
    StockBalance stockBalance,
    StockFacilityInfo facility,
    StockDepartamentInfo departament,
    StockItemInfo item
) {
    
}
