package com.marcos.estik.stock.application.ports.in;

import java.util.List;

import com.marcos.estik.stock.application.command.EntryPurchaseCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

public interface EntryPurchasePort {
    
    List<StockMovementView> execute(EntryPurchaseCommand command);

}
