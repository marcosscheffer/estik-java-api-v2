package com.marcos.estik.computerhistory.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaComputerHistoryRepository 
    extends JpaRepository<ComputerHistoryEntity, UUID>, JpaSpecificationExecutor<ComputerHistoryEntity> {
    
}
