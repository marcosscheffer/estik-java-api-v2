package com.marcos.estik.facility.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, UUID>{

    Page<FacilityEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
