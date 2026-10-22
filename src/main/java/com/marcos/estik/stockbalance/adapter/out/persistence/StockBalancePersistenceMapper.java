package com.marcos.estik.stockbalance.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.stockbalance.domain.StockBalance;

@Mapper(componentModel = "spring")
public interface StockBalancePersistenceMapper {
    
    StockBalanceEntity toEntity(StockBalance stockBalance);

    StockBalance toDomain(StockBalanceEntity stockBalance);
    
}
