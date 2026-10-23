package com.marcos.estik.stock.application.view;

import com.marcos.estik.stock.domain.StockMovement;

public record StockMovementView(
    StockMovement stockMovement,
    StockItemInfo item,
    StockFacilityInfo facilitySource,
    StockFacilityInfo facilityDestination,
    StockDepartamentInfo departamentSource,
    StockDepartamentInfo departamentDestination
) {
    
}
