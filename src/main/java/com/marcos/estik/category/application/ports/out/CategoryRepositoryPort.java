package com.marcos.estik.category.application.ports.out;

import java.util.UUID;

import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface CategoryRepositoryPort {
    Category save(Category category);

    Category findById(UUID categoryId);

    Boolean existsById(UUID categoryId);

    PageResult<Category> searchCategory(
        String name,
        PageQuery page
    );
}
