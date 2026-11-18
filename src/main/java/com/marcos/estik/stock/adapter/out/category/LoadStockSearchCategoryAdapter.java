package com.marcos.estik.stock.adapter.out.category;

import org.springframework.stereotype.Component;

import com.marcos.estik.category.application.command.SearchCategoryCommand;
import com.marcos.estik.category.application.ports.in.SearchCategoryPort;
import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.application.ports.out.LoadStockSearchCategoryPort;
import com.marcos.estik.stock.application.view.StockCategoryInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockSearchCategoryAdapter implements LoadStockSearchCategoryPort {

    private final SearchCategoryPort searchCategoryPort;
    private final StockCategoryMapper stockCategoryMapper;

    @Override
    public PageResult<StockCategoryInfo> execute(String name, PageQuery page) {
        PageResult<Category> categories = searchCategoryPort.execute(
            new SearchCategoryCommand(
                name,
                page
            )
        );

        return categories.map(stockCategoryMapper::toInfo);
    }
}
