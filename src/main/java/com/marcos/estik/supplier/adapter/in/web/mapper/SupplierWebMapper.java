package com.marcos.estik.supplier.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.supplier.adapter.in.web.response.SupplierResponse;
import com.marcos.estik.supplier.domain.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierWebMapper {

    SupplierResponse toResponse(Supplier supplier);
    
}
