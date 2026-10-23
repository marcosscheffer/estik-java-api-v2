package com.marcos.estik.ticket.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.TicketHistory;

@Mapper(componentModel = "spring")
public interface TicketHistoryPersistenceMapper {

    TicketHistoryEntity toEntity(TicketHistory ticketHistory);

    TicketHistory toDomain(TicketHistoryEntity ticketHistory);
    
}
