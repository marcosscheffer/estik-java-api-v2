package com.marcos.estik.stockbalance.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stockbalance.domain.StockBalance;

public interface FindStockBalanceByIdPort {
    StockBalance execute(UUID id);
}
