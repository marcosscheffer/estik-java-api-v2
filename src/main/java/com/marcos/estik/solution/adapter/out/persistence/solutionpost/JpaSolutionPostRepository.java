package com.marcos.estik.solution.adapter.out.persistence.solutionpost;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaSolutionPostRepository 
    extends JpaRepository<SolutionPostEntity, UUID>, JpaSpecificationExecutor<SolutionPostEntity> {
    
}
