package com.marcos.estik.supplier.application.port.in;

import java.util.UUID;

public interface DeleteSupplierByIdPort {
    
    void execute(UUID id);

}
