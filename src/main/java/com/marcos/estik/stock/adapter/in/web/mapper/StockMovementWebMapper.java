package com.marcos.estik.stock.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.stock.adapter.in.web.request.MovementRequest;
import com.marcos.estik.stock.adapter.in.web.request.TransferRequest;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementSummaryResponse;
import com.marcos.estik.stock.application.command.MovementCommand;
import com.marcos.estik.stock.application.command.TransferCommand;
import com.marcos.estik.stock.application.view.StockMovementView;

@Mapper(componentModel = "spring")
public interface StockMovementWebMapper {
    @Mapping(target = ".", source = "stockMovement")
    StockMovementSummaryResponse toSummaryResponse(StockMovementView stockMovement);    
    
    @Mapping(target = ".", source = "stockMovement")
    StockMovementResponse toResponse(StockMovementView stockMovement);

    MovementCommand toMovementCommand(MovementRequest request);

    TransferCommand toTransferCommand(TransferRequest request);

}
