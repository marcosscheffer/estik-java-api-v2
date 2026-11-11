package com.marcos.estik.specialization.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaSpecializationRepository 
    extends JpaRepository<SpecializationEntity, UUID>, JpaSpecificationExecutor<SpecializationEntity> {
    
}
