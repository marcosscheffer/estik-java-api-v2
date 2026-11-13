package com.marcos.estik.ticket.adapter.out.persistence.forwarded;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.Forwarded;

@Mapper(componentModel = "spring")
public interface ForwardedPersistenceMapper {

    Forwarded toDomain(ForwardedEntity forwarded);

    ForwardedEntity toEntity(Forwarded forwarded);
    
}
