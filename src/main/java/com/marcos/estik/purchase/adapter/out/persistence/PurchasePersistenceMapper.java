package com.marcos.estik.purchase.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.purchase.domain.Purchase;

@Mapper(componentModel = "spring")
public interface PurchasePersistenceMapper {
    
    Purchase toDomain(PurchaseEntity purchase);

    PurchaseEntity toEntity(Purchase purchase);
    
}
