package com.marcos.estik.stock.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.marcos.estik.stock.domain.StockBalance;

public interface JpaStockBalanceRepository 
    extends JpaRepository<StockBalanceEntity, UUID>, JpaSpecificationExecutor<StockBalanceEntity> {

    Boolean existsByFacilityIdAndDepartamentIdAndItemId(
        UUID facilityId,
        UUID departamentId,
        UUID itemId
    );

    Optional<StockBalance> findByFacilityIdAndDepartamentIdAndItemId(
        UUID facilityId, 
        UUID departamentId, 
        UUID itemId
    );
    
}
