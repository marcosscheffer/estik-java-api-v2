package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.ports.in.DeleteItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteItemUseCase implements DeleteItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;
    
    @Override
    public void execute(UUID id) {
        itemRepositoryPort.deleteById(id);
    }
    
}
