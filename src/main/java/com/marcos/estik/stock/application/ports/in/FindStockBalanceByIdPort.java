package com.marcos.estik.stock.application.ports.in;

import java.util.UUID;

import com.marcos.estik.stock.application.view.StockBalanceView;

public interface FindStockBalanceByIdPort {

    StockBalanceView execute(UUID id);

}
