package com.marcos.estik.category.application.usecases;


import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.command.UpdateCategoryCommand;
import com.marcos.estik.category.application.ports.in.UpdateCategoryPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;
import com.marcos.estik.category.domain.Category;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateCategoryUseCase implements UpdateCategoryPort {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category execute(UUID id, UpdateCategoryCommand command) {
        Category category = categoryRepositoryPort.findById(id);
        category.updateInfo(
            command.name(), 
            command.description()    
        );
        return categoryRepositoryPort.save(category);
    }
}
