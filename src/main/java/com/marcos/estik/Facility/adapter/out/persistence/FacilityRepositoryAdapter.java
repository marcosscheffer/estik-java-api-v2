package com.marcos.estik.Facility.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.Facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.Facility.domain.Facility;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class FacilityRepositoryAdapter implements FacilityRepositoryPort{

    private final JpaFacilityRepository jpaFacilityRepository;
    private final FacilityPersistenceMapper facilityPersistenceMapper;

    @Override
    public Facility save(Facility facility) {
        FacilityEntity facilityEntity = facilityPersistenceMapper.toEntity(facility);
        jpaFacilityRepository.save(facilityEntity);
        return facilityPersistenceMapper.toDomain(facilityEntity);
    }

    @Override
    public Facility findFacilityById(UUID id) {
        return jpaFacilityRepository.findById(id)
            .map(facilityPersistenceMapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("Facility Id not found"));
    }
    
}
