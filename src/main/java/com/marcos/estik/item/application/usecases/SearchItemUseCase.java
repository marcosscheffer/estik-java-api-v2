package com.marcos.estik.item.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.SearchItemCommand;
import com.marcos.estik.item.application.ports.in.SearchItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.application.ports.out.LoadItemFindCategoryByIdPort;
import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.item.domain.Item;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchItemUseCase implements SearchItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;
    private final LoadItemFindCategoryByIdPort loadItemFindCategoryByIdPort;
    
    @Override
    public PageResult<ItemView> execute(SearchItemCommand command) {
        PageResult<Item> items = itemRepositoryPort.SearchItem(
            command.name(), 
            command.sku(),
            command.categoryId(),
            command.page()
        );

        return items.map(item -> new ItemView(
            item,
            loadItemFindCategoryByIdPort.execute(item.getCategoryId())
        ));
    }
}
