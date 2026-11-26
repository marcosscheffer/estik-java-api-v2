package com.marcos.estik.stock.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.stock.domain.TypeMovementEnum;

public record SearchStockMovementCommand(
    UUID facilityId,
    UUID departamentId,
    UUID purchaseId,
    TypeMovementEnum type,
    PageQuery page
) {
    
}
