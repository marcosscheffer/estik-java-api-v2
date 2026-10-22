package com.marcos.estik.stock.adapter.out.persistence.stockbalance;

import org.mapstruct.Mapper;

import com.marcos.estik.stock.domain.StockBalance;

@Mapper(componentModel = "spring")
public interface StockBalancePersistenceMapper {

    StockBalanceEntity toEntity(StockBalance stockBalance);

    StockBalance toDomain(StockBalanceEntity stockBalance);
    
}
