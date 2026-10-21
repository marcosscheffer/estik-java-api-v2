package com.marcos.estik.stockbalance.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchStockBalanceCommand(
    UUID facilityId,
    UUID departamentId,
    UUID itemId,
    PageQuery page
) {
    
}
