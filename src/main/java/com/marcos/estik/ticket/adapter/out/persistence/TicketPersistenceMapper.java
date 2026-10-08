package com.marcos.estik.ticket.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.Ticket;

@Mapper(componentModel = "spring")
public interface TicketPersistenceMapper {
    TicketEntity toEntity(Ticket ticket);

    Ticket toDomain(TicketEntity ticket);
}
