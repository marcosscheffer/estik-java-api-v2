package com.marcos.estik.supplier.application.port.in;

import java.util.UUID;

import com.marcos.estik.supplier.domain.Supplier;

public interface FindSupplierByIdPort {
    
    Supplier execute(UUID id);

}
