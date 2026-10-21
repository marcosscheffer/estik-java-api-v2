package com.marcos.estik.stockbalance.application.ports.out;

import java.util.UUID;

public interface LoadStockBalanceExistsFacilityByIdPort {
    Boolean existsById(UUID id);
    
}
