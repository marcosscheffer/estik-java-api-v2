package com.marcos.estik.stock.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockMovementView;

public interface FindStockMovementByIdPort {
    StockMovementView execute(UUID id);
}
