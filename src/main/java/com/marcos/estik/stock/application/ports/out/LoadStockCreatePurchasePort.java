package com.marcos.estik.stock.application.ports.out;

import com.marcos.estik.stock.application.command.CreateStockPurchaseCommand;
import com.marcos.estik.stock.application.view.StockPurchaseInfo;

public interface LoadStockCreatePurchasePort {
    
    StockPurchaseInfo execute(CreateStockPurchaseCommand command);

}
