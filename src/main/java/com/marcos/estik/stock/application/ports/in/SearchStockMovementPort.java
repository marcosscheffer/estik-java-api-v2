package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.command.SearchStockMovementCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

public interface SearchStockMovementPort {
    PageResult<StockMovementView> execute(SearchStockMovementCommand command);
}
