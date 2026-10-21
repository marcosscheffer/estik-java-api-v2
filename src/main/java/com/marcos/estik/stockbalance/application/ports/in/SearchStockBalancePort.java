package com.marcos.estik.stockbalance.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stockbalance.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stockbalance.domain.StockBalance;

public interface SearchStockBalancePort {
    PageResult<StockBalance> execute(SearchStockBalanceCommand command);
}
