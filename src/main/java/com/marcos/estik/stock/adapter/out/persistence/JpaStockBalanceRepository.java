package com.marcos.estik.stock.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
        SELECT COALESCE(SUM(b.quantity), 0L)
        FROM StockBalanceEntity b 
        JOIN ItemEntity i ON i.id = b.itemId
        WHERE i.categoryId = :categoryId
    """)
    Long sumByCategory(@Param("categoryId") UUID categoryId);
    
}
