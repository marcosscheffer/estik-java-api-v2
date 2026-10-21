package com.marcos.estik.stockbalance.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stockbalance.domain.StockBalance;

public interface ExitStockBalancePort {
    StockBalance execute(UUID id, Long quantity);
}
