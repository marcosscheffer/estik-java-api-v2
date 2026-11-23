package com.marcos.estik.supplier.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.supplier.domain.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierPersistenceMapper {

    SupplierEntity toEntity(Supplier supplier);

    Supplier toDomain(SupplierEntity supplier);
    
}
