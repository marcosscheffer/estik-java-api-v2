package com.marcos.estik.facility.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

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
        FacilityEntity facilitySaved = jpaFacilityRepository.save(facilityEntity);
        return facilityPersistenceMapper.toDomain(facilitySaved);
    }

    @Override
    public Facility findFacilityById(UUID id) {
        return jpaFacilityRepository.findById(id)
            .map(facilityPersistenceMapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("Facility not found"));
    }

    @Override
    public PageResult<Facility> SearchFacilityByName(String name, PageQuery page) {
        Page<FacilityEntity> facilities = jpaFacilityRepository.findByNameContainingIgnoreCase(
            name,
            PageRequest.of(
                page.page(),
                page.size()
            )
        );
        return new PageResult<Facility>(
            facilities.getContent().stream().map(facilityPersistenceMapper::toDomain).toList(),
            facilities.getNumber(),
            facilities.getSize(),
            facilities.getTotalElements(),
            facilities.getTotalPages()
        );
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaFacilityRepository.existsById(id);
    }

    @Override
    public void deleteFacilityById(UUID id) {
        jpaFacilityRepository.deleteById(id);
    }
}
