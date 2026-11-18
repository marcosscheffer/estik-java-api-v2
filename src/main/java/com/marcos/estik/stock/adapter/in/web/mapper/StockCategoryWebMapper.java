package com.marcos.estik.stock.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.stock.adapter.in.web.response.StockCategoryResponse;
import com.marcos.estik.stock.application.view.StockCategoryView;

@Mapper(componentModel = "spring")
public interface StockCategoryWebMapper {

    StockCategoryResponse toResponse(StockCategoryView categoryView);
    
}
