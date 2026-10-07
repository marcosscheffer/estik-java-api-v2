package com.marcos.estik.facility.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.domain.Facility;

@Mapper(componentModel = "spring")
public interface FacilityPersistenceMapper {
    Facility toDomain(FacilityEntity facility);

    FacilityEntity toEntity(Facility facility);
    
}
