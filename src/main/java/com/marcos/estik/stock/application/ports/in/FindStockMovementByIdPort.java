package com.marcos.estik.stock.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stock.domain.StockMovement;

public interface FindStockMovementByIdPort {
    StockMovement execute(UUID id);
}
