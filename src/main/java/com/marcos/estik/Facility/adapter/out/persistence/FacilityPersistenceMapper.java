package com.marcos.estik.Facility.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.Facility.domain.Facility;

@Mapper(componentModel = "spring")
public interface FacilityPersistenceMapper {
    Facility toDomain(FacilityEntity facility);

    FacilityEntity toEntity(Facility facility);
    
}
