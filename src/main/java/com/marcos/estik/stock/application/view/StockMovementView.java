package com.marcos.estik.stock.application.view;

import com.marcos.estik.stock.domain.StockMovement;

public record StockMovementView(
    StockMovement stockMovement,
    StockPurchaseInfo purchase,
    StockItemInfo item,
    StockFacilityInfo facilitySource,
    StockFacilityInfo facilityDestination,
    StockDepartamentInfo departamentSource,
    StockDepartamentInfo departamentDestination
) {
    
}
