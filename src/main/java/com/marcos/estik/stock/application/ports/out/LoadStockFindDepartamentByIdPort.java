package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockDepartamentInfo;

public interface LoadStockFindDepartamentByIdPort {
    
    StockDepartamentInfo execute(UUID id);

}
