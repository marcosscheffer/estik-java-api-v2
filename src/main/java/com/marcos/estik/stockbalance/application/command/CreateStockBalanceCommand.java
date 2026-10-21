package com.marcos.estik.stockbalance.application.command;

import java.util.UUID;

public record CreateStockBalanceCommand(
    UUID facilityId,
    UUID departamentId,
    UUID itemId,
    Long quantity
) {
    
}
