package com.marcos.estik.stock.application.ports.in;


import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

public interface EntryStockPort {

    StockMovementView execute(MovementCommand command);

}
