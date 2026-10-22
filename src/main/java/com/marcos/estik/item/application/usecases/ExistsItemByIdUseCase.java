package com.marcos.estik.item.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.ports.in.ExistsItemByIdPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsItemByIdUseCase implements ExistsItemByIdPort {

    private final ItemRepositoryPort itemRepositoryPort;

    @Override
    public Boolean execute(UUID id) {
        return itemRepositoryPort.existsById(id);
    }
}
