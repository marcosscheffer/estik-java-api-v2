package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stock.domain.StockBalance;

public interface SearchStockBalancePort {
    PageResult<StockBalance> execute(SearchStockBalanceCommand command);
}
