package com.marcos.estik.category.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.ports.in.DeleteCategoryPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteCategoryUseCase implements DeleteCategoryPort {
    
    private final CategoryRepositoryPort categoryRepositoryPort;


    @Override
    public void execute(UUID id) {
        categoryRepositoryPort.deleteById(id);
    }
    
}
