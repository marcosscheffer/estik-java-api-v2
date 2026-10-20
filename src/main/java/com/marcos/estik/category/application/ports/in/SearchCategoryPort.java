package com.marcos.estik.category.application.ports.in;

import com.marcos.estik.category.application.command.SearchCategoryCommand;
import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchCategoryPort {
    PageResult<Category> execute(SearchCategoryCommand command);
}
