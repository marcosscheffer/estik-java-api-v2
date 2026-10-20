package com.marcos.estik.category.application.ports.in;


import com.marcos.estik.category.application.command.CreateCategoryCommand;
import com.marcos.estik.category.domain.Category;

public interface CreateCategoryPort {
    Category execute(CreateCategoryCommand command);
}
