package com.marcos.estik.purchase.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.purchase.adapter.in.web.response.PurchaseResponse;
import com.marcos.estik.purchase.adapter.in.web.response.PurchaseSummaryResponse;
import com.marcos.estik.purchase.application.view.PurchaseView;

@Mapper(componentModel = "spring")
public interface PurchaseWebMapper {

    @Mapping(target = ".", source = "purchase")
    PurchaseResponse toResponse(PurchaseView purchase);
    
    @Mapping(target = ".", source = "purchase")
    PurchaseSummaryResponse toSummaryResponse(PurchaseView purchase);
    
}
