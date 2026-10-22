package com.marcos.estik.stock.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stock.domain.StockBalance;

public interface FindStockBalanceByIdPort {
    StockBalance execute(UUID id);
}
