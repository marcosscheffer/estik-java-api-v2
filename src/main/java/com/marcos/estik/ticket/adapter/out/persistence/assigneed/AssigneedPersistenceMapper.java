package com.marcos.estik.ticket.adapter.out.persistence.assigneed;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.Assigneed;

@Mapper(componentModel = "spring")
public interface AssigneedPersistenceMapper {

    AssigneedEntity toEntity(Assigneed assigneed);

    Assigneed toDomain(AssigneedEntity assigneed);
    
}
