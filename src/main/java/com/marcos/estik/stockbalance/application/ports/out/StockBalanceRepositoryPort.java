package com.marcos.estik.stockbalance.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stockbalance.domain.StockBalance;

public interface StockBalanceRepositoryPort {
    StockBalance save(StockBalance stockBalance);

    StockBalance findById(UUID id);

    Boolean existsByFacilityIdAndDepartamentIdAndItemId(
        UUID facilityId,
        UUID departamentId,
        UUID itemId
    );
    
    PageResult<StockBalance> SearchStockBalance(
        UUID facilityId,
        UUID departamentId,
        UUID itemId,
        PageQuery page
    );
}
