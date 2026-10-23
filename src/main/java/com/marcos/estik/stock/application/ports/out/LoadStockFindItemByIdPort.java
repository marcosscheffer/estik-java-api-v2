package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockItemInfo;

public interface LoadStockFindItemByIdPort {
    
    StockItemInfo execute(UUID id);

}
