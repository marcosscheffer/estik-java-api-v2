package com.marcos.estik.category.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.category.adapter.in.web.request.CreateCategoryRequest;
import com.marcos.estik.category.adapter.in.web.request.UpdateCategoryRequest;
import com.marcos.estik.category.adapter.in.web.response.CategoryResponse;
import com.marcos.estik.category.adapter.in.web.response.CategorySummaryResponse;
import com.marcos.estik.category.application.command.CreateCategoryCommand;
import com.marcos.estik.category.application.command.UpdateCategoryCommand;
import com.marcos.estik.category.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {
    CategoryResponse toResponse(Category category);

    CategorySummaryResponse toSummaryResponse(Category category);

    CreateCategoryCommand toCreateCategoryCommand(CreateCategoryRequest request);

    UpdateCategoryCommand toUpdateCategoryCommand(UpdateCategoryRequest request);
    
}
