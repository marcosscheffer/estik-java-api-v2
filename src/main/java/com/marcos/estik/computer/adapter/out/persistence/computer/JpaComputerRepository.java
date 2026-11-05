package com.marcos.estik.computer.adapter.out.persistence.computer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaComputerRepository 
    extends JpaRepository<ComputerEntity, UUID>, JpaSpecificationExecutor<ComputerEntity> {
    
}
