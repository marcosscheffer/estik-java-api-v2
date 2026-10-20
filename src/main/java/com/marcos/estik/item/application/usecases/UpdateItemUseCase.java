package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.application.ports.in.UpdateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.application.ports.out.LoadItemExistsCategoryByIdPort;
import com.marcos.estik.item.domain.Item;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateItemUseCase implements UpdateItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;
    private final LoadItemExistsCategoryByIdPort loadItemExistsCategoryByIdPort;

    @Override
    public Item execute(UUID id, UpdateItemCommand command) {
        if(!loadItemExistsCategoryByIdPort.existsById(command.categoryId())) {
            throw new EntityNotFoundException("Category not found");
        }

        Item item = itemRepositoryPort.findById(id);
        item.updateInfo(
            command.name(),
            command.description(),
            command.sku(),
            command.minimum(),
            command.categoryId()
        );
        return itemRepositoryPort.save(item);
    }
}
