package com.marcos.estik.stock.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.stock.adapter.in.web.response.StockBalanceResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockBalanceSummaryResponse;
import com.marcos.estik.stock.application.view.StockBalanceView;

@Mapper(componentModel = "spring")
public interface StockBalanceWebMapper {
    
    @Mapping(target = ".", source = "stockBalance")
    StockBalanceResponse toResponse(StockBalanceView stockBalance);

    @Mapping(target = ".", source = "stockBalance")
    StockBalanceSummaryResponse toSummaryResponse(StockBalanceView stockBalance);


}
