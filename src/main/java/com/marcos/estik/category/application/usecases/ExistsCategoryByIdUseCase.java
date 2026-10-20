package com.marcos.estik.category.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.ports.in.ExistsCategoryByIdPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsCategoryByIdUseCase implements ExistsCategoryByIdPort {
    
    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Boolean execute(UUID id) {
        return categoryRepositoryPort.existsById(id);
    }
    
}
