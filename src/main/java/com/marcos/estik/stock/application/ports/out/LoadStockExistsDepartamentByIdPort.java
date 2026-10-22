package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

public interface LoadStockExistsDepartamentByIdPort {
    
    Boolean existsById(UUID id);

}
