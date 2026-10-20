package com.marcos.estik.item.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.ports.in.CreateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.domain.Item;

import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class CreateItemUseCase implements CreateItemPort {

    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Item execute(CreateItemCommand command) {
        Item item = Item.create(
            command.name(), 
            command.description(), 
            command.code()
        );
        return itemRepositoryPort.save(item);
    }
    
}
