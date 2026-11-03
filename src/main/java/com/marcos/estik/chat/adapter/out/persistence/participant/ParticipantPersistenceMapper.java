package com.marcos.estik.chat.adapter.out.persistence.participant;

import org.mapstruct.Mapper;

import com.marcos.estik.chat.domain.Participant;

@Mapper(componentModel = "spring")
public interface ParticipantPersistenceMapper {

    ParticipantEntity toEntity(Participant participant);

    Participant toDomain(ParticipantEntity participant);
    
}
