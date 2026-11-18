package com.marcos.estik.stock.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.view.StockCategoryView;

public interface SearchStockCategoryPort {
    
    PageResult<StockCategoryView> execute(String name, PageQuery page);

}
