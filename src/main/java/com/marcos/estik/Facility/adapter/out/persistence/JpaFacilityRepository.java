package com.marcos.estik.Facility.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, UUID>{
    
}
