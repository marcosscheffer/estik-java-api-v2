package com.marcos.estik.item.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.ports.in.CreateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.application.ports.out.LoadItemExistsCategoryByIdPort;
import com.marcos.estik.item.domain.Item;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class CreateItemUseCase implements CreateItemPort {

    private final ItemRepositoryPort itemRepositoryPort;
    private final LoadItemExistsCategoryByIdPort loadItemExistsCategoryByIdPort;

    @Override
    public Item execute(CreateItemCommand command) {
        if(!loadItemExistsCategoryByIdPort.existsById(command.categoryId())) {
            throw new EntityNotFoundException("Category not found");
        }
        
        Item item = Item.create(
            command.name(), 
            command.description(), 
            command.sku(),
            command.minimum(),
            command.categoryId()
        );
        return itemRepositoryPort.save(item);
    }
    
}
