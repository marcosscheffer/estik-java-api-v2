package com.marcos.estik.category.application.usecases;


import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.command.CreateCategoryCommand;
import com.marcos.estik.category.application.ports.in.CreateCategoryPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;
import com.marcos.estik.category.domain.Category;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateCategoryUseCase implements CreateCategoryPort {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category execute(CreateCategoryCommand command) {
        Category category = Category.create(
            command.name(), 
            command.description()
        );

        return categoryRepositoryPort.save(category);
    }
    
}
