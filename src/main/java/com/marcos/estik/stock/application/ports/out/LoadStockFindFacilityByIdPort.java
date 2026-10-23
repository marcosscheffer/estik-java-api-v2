package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockFacilityInfo;

public interface LoadStockFindFacilityByIdPort {
    
    StockFacilityInfo execute(UUID id);

}
