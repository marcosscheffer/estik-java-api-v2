package com.marcos.estik.ticket.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.TicketMessage;

@Mapper(componentModel = "spring")
public interface TicketMessagePersistenceMapper {

    TicketMessageEntity toEntity(TicketMessage ticketMessage);

    TicketMessage toDomain(TicketMessageEntity ticketMessage);
    
}
