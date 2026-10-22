package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

public interface LoadStockExistsItemByIdPort {
    
    Boolean existsById(UUID id);
    
}
