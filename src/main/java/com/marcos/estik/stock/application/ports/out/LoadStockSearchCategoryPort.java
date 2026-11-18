package com.marcos.estik.stock.application.ports.out;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.view.StockCategoryInfo;

public interface LoadStockSearchCategoryPort {
    
    PageResult<StockCategoryInfo> execute(String name, PageQuery page);

}
