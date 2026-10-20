package com.marcos.estik.category.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.category.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity category);
    
}
