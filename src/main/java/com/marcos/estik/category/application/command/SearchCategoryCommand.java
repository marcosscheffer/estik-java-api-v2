package com.marcos.estik.category.application.command;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchCategoryCommand(
    String name,
    PageQuery page
) {
    
}
