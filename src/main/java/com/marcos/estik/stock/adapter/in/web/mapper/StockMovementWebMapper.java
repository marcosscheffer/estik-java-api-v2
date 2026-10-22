package com.marcos.estik.stock.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.stock.adapter.in.web.response.StockMovementResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementSummaryResponse;
import com.marcos.estik.stock.domain.StockMovement;

@Mapper(componentModel = "spring")
public interface StockMovementWebMapper {
    StockMovementSummaryResponse toSummaryResponse(StockMovement stockMovement);

    StockMovementResponse toResponse(StockMovement stockMovement);
}
