package com.marcos.estik.stock.adapter.out.persistence.stockmovement;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaStockMovementRepository 
    extends JpaRepository<StockMovementEntity, UUID>, JpaSpecificationExecutor<StockMovementEntity> {
    
}
