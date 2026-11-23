package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.stock.application.command.EntryPurchaseCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

public interface EntryPurchasePort {
    
    StockMovementView execute(EntryPurchaseCommand command);

}
