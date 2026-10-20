package com.marcos.estik.category.application.command;

public record CreateCategoryCommand(
    String name,
    String description
) {
    
}
