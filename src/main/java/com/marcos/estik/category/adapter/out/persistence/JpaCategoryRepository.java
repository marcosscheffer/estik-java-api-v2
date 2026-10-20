package com.marcos.estik.category.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaCategoryRepository 
    extends JpaRepository<CategoryEntity, UUID>, JpaSpecificationExecutor<CategoryEntity> {
    
}
