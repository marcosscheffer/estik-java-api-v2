package com.marcos.estik.solution.adapter.out.persistence.solutionresponse;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSolutionReponseRepository extends JpaRepository<SolutionResponseEntity, UUID> {

    Page<SolutionResponseEntity> findBySolutionPostId(UUID solutionPostId, Pageable pageable);
    
}
