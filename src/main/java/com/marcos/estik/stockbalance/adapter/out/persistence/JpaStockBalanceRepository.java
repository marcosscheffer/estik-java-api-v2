package com.marcos.estik.stockbalance.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaStockBalanceRepository 
    extends JpaRepository<StockBalanceEntity, UUID>, JpaSpecificationExecutor<StockBalanceEntity> {

    Boolean existsByFacilityIdAndDepartamentIdAndItemId(
        UUID facilityId,
        UUID departamentId,
        UUID itemId
    );
    
}
