package com.marcos.estik.specialization.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.specialization.domain.Specialization;

@Mapper(componentModel = "spring")
public interface SpecializationPersistenceMapper {

    SpecializationEntity toEntity(Specialization Specialization);

    Specialization toDomain(SpecializationEntity specialization);
    
}
