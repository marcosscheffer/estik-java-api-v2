package com.marcos.estik.stock.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.stock.adapter.in.web.response.StockBalanceResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockBalanceSummaryResponse;
import com.marcos.estik.stock.domain.StockBalance;

@Mapper(componentModel = "spring")
public interface StockBalanceWebMapper {
    
    StockBalanceResponse toResponse(StockBalance stockBalance);

    StockBalanceSummaryResponse toSummaryResponse(StockBalance stockBalance);


}
