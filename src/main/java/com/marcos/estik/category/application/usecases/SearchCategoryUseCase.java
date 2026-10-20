package com.marcos.estik.category.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.category.application.command.SearchCategoryCommand;
import com.marcos.estik.category.application.ports.in.SearchCategoryPort;
import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;
import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchCategoryUseCase implements SearchCategoryPort {

    private final CategoryRepositoryPort categoryRepositoryPort;
    
    @Override
    public PageResult<Category> execute(SearchCategoryCommand command) {
        return categoryRepositoryPort.searchCategory(
            command.name(), 
            command.page()
        );
    }
}
