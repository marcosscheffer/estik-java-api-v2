package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.application.ports.in.UpdateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.application.ports.out.LoadItemFindCategoryByIdPort;
import com.marcos.estik.item.application.view.ItemCategoryInfo;
import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.item.domain.Item;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateItemUseCase implements UpdateItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;
    private final LoadItemFindCategoryByIdPort loadItemFindCategoryByIdPort;

    @Override
    public ItemView execute(UUID id, UpdateItemCommand command) {
        ItemCategoryInfo categoryInfo = loadItemFindCategoryByIdPort.execute(command.categoryId());

        Item item = itemRepositoryPort.findById(id);
        item.updateInfo(
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
