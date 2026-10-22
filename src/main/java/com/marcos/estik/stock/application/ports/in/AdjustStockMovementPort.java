package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.domain.StockMovement;

public interface AdjustStockMovementPort {

    StockMovement execute(MovementCommand command);
    
}