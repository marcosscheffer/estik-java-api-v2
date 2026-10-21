package com.marcos.estik.stockbalance.application.ports.out;

import java.util.UUID;

public interface LoadStockBalanceExistsItemByIdPort {
    Boolean existsById(UUID id);
    
}
