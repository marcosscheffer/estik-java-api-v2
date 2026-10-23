package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.stock.application.command.TransferCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

public interface TransferStockPort {

    StockMovementView execute(TransferCommand command);
    
}
