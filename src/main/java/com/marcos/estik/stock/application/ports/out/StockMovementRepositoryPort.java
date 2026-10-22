package com.marcos.estik.stock.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.domain.StockMovement;
import com.marcos.estik.stock.domain.TypeMovementEnum;

public interface StockMovementRepositoryPort {

    StockMovement save(StockMovement stockMovement);

    StockMovement findById(UUID id);

    PageResult<StockMovement> searchStockMovement(
        UUID facilityId, 
        UUID departamentId, 
        TypeMovementEnum type,
        PageQuery page
    );
}
