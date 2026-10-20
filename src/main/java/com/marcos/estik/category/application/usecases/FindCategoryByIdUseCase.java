package com.marcos.estik.category.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.ports.in.FindCategoryByIdPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;
import com.marcos.estik.category.domain.Category;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindCategoryByIdUseCase implements FindCategoryByIdPort {
    
    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category execute(UUID id) {
        return categoryRepositoryPort.findById(id);
    }
    
}
