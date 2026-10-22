package com.marcos.estik.stock.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stock.application.command.TransferMovementCommand;
import com.marcos.estik.stock.domain.StockMovement;

public interface TransferStockMovementPort {
    StockMovement execute(UUID StockBalanceId, TransferMovementCommand command);
}
