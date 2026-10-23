package com.marcos.estik.ticket.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.adapter.in.web.response.TicketHistoryResponse;
import com.marcos.estik.ticket.domain.TicketHistory;

@Mapper(componentModel = "spring")
public interface TicketHistoryWebMapper {

    TicketHistoryResponse toResponse(TicketHistory ticketHistory);
    
}
