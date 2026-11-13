package com.marcos.estik.ticket.adapter.out.persistence.tickethistory;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.TicketHistory;

@Mapper(componentModel = "spring")
public interface TicketHistoryPersistenceMapper {

    TicketHistoryEntity toEntity(TicketHistory ticketHistory);

    TicketHistory toDomain(TicketHistoryEntity ticketHistory);
    
}
