package com.marcos.estik.stock.adapter.out.departament;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.stock.application.view.StockDepartamentInfo;

@Mapper(componentModel = "spring")
public interface StockDepartamentMapper {
    
    @Mapping(target = ".", source = "departament")
    StockDepartamentInfo toDepartamentInfo(DepartamentView departament);
    
}
