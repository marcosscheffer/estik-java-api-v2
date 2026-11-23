package com.marcos.estik.stock.adapter.out.purchase;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.stock.application.view.StockPurchaseInfo;

@Mapper(componentModel = "spring")
public interface StockPurchaseMapper {
    
    @Mapping(target = ".", source = "purchase")
    StockPurchaseInfo toInfo(PurchaseView purchase);

}
