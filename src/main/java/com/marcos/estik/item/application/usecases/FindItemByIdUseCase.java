package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.ports.in.FindItemByIdPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.domain.Item;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindItemByIdUseCase implements FindItemByIdPort {
    
    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Item execute(UUID id) {
        return itemRepositoryPort.findById(id);
    }
    
}
