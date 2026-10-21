package com.marcos.estik.stockbalance.application.ports.out;

import java.util.UUID;

public interface LoadStockBalanceExistsDepartamentByIdPort {
    Boolean existsById(UUID id);
    
}