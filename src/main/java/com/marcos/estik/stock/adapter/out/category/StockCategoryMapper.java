package com.marcos.estik.stock.adapter.out.category;

import org.mapstruct.Mapper;

import com.marcos.estik.category.domain.Category;
import com.marcos.estik.stock.application.view.StockCategoryInfo;

@Mapper(componentModel = "spring")
public interface StockCategoryMapper {

    StockCategoryInfo toInfo(Category category);
    
}
