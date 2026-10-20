package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.application.ports.in.UpdateItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.domain.Item;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateItemUseCase implements UpdateItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Item execute(UUID id, UpdateItemCommand command) {
        Item item = itemRepositoryPort.findById(id);
        item.updateInfo(
            command.name(),
            command.description(),
            command.code()
        );
        return itemRepositoryPort.save(item);
    }
}
