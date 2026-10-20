package com.marcos.estik.category.application.ports.in;

import java.util.UUID;

import com.marcos.estik.category.application.command.UpdateCategoryCommand;
import com.marcos.estik.category.domain.Category;

public interface UpdateCategoryPort {
    Category execute(UUID id, UpdateCategoryCommand command);
}
