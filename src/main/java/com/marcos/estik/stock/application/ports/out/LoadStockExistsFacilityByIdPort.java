package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

public interface LoadStockExistsFacilityByIdPort {
    
    Boolean existsById(UUID id);

}
