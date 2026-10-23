package com.marcos.estik.item.adapter.out.category;

import org.mapstruct.Mapper;

import com.marcos.estik.category.domain.Category;
import com.marcos.estik.item.application.view.ItemCategoryInfo;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper {
    
    ItemCategoryInfo toCategoryInfo(Category category);

}
