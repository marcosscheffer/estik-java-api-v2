package com.marcos.estik.item.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.ports.in.CreateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.application.ports.out.LoadItemFindCategoryByIdPort;
import com.marcos.estik.item.application.view.ItemCategoryInfo;
import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.item.domain.Item;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateItemUseCase implements CreateItemPort {

    private final ItemRepositoryPort itemRepositoryPort;
    private final LoadItemFindCategoryByIdPort loadItemFindCategoryByIdPort;

    @Override
    public ItemView execute(CreateItemCommand command) {
        ItemCategoryInfo categoryInfo = loadItemFindCategoryByIdPort.execute(command.categoryId());
        
        Item item = Item.create(
            command.name(), 
            command.description(), 
            command.sku(),
            command.minimum(),
            command.categoryId()
        );

        itemRepositoryPort.save(item);

        return new ItemView(
            item,
            categoryInfo
        );
    }
}
