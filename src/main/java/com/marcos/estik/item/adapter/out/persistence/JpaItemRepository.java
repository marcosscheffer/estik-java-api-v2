package com.marcos.estik.item.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaItemRepository 
    extends JpaRepository<ItemEntity, UUID>, JpaSpecificationExecutor<ItemEntity> {
    
}
