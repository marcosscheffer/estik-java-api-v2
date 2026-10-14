package com.marcos.estik.departament.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaDepartamentRepository 
    extends JpaRepository<DepartamentEntity, UUID>, JpaSpecificationExecutor<DepartamentEntity> {
    
}
