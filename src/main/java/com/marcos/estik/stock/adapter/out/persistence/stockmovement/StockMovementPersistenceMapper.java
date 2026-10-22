package com.marcos.estik.stock.adapter.out.persistence.stockmovement;

import org.mapstruct.Mapper;

import com.marcos.estik.stock.domain.StockMovement;

@Mapper(componentModel = "spring")
public interface StockMovementPersistenceMapper {
    StockMovementEntity toEntity(StockMovement stockMovement);

    StockMovement toDomain(StockMovementEntity stockMovement);
    
}
