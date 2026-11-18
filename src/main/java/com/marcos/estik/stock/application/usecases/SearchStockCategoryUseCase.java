package com.marcos.estik.stock.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.ports.in.SearchStockCategoryPort;
import com.marcos.estik.stock.application.ports.out.LoadStockSearchCategoryPort;
import com.marcos.estik.stock.application.ports.out.StockBalanceRepositoryPort;
import com.marcos.estik.stock.application.view.StockCategoryInfo;
import com.marcos.estik.stock.application.view.StockCategoryView;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchStockCategoryUseCase implements SearchStockCategoryPort {

    private final LoadStockSearchCategoryPort loadStockSearchCategoryPort;
    private final StockBalanceRepositoryPort stockBalanceRepositoryPort;

    @Override
    public PageResult<StockCategoryView> execute(String name, PageQuery page) {
        PageResult<StockCategoryInfo> categories = loadStockSearchCategoryPort.execute(name, page);
        return categories.map(
            category -> new StockCategoryView(
            category.id(), 
            category.name(), 
            category.description(),
            category.minimum(),
            stockBalanceRepositoryPort.sumQuantityByCategory(category.id())
            )
        );
    }
}
