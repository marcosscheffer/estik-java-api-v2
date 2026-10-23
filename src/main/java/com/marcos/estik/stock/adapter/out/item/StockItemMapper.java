package com.marcos.estik.stock.adapter.out.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.stock.application.view.StockItemInfo;

@Mapper(componentModel = "spring")
public interface StockItemMapper {
    
    @Mapping(target = ".", source = "item")
    StockItemInfo toItemInfo(ItemView item);

}
