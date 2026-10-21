package com.marcos.estik.stockbalance.application.ports.in;

import com.marcos.estik.stockbalance.application.command.CreateStockBalanceCommand;
import com.marcos.estik.stockbalance.domain.StockBalance;

public interface CreateStockBalancePort {

    StockBalance execute(CreateStockBalanceCommand command);
    
}
