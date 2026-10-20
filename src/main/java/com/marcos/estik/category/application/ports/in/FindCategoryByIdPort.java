package com.marcos.estik.category.application.ports.in;

import java.util.UUID;

import com.marcos.estik.category.domain.Category;

public interface FindCategoryByIdPort {
    Category execute(UUID id);
}
