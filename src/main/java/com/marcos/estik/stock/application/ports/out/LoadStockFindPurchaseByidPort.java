package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockPurchaseInfo;

public interface LoadStockFindPurchaseByidPort {
    
    StockPurchaseInfo execute(UUID id);

}
