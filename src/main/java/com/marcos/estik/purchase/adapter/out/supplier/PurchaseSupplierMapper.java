package com.marcos.estik.purchase.adapter.out.supplier;

import org.mapstruct.Mapper;

import com.marcos.estik.purchase.application.view.PurchaseSupplierInfo;
import com.marcos.estik.supplier.domain.Supplier;

@Mapper(componentModel = "spring")
public interface PurchaseSupplierMapper {

    PurchaseSupplierInfo toInfo(Supplier supplier);
    
}
