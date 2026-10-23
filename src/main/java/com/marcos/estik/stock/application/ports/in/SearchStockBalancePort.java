package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stock.application.view.StockBalanceView;

public interface SearchStockBalancePort {

    PageResult<StockBalanceView> execute(SearchStockBalanceCommand command);

}
