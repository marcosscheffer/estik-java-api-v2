package com.marcos.estik.supplier.application.port.in;

import com.marcos.estik.supplier.domain.Supplier;

public interface CreateSupplierPort {
    
    Supplier execute(String name);

}
