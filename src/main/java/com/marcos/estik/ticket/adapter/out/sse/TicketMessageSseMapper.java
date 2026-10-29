package com.marcos.estik.ticket.adapter.out.sse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.ticket.application.view.TicketMessageView;

@Mapper(componentModel = "spring")
public interface TicketMessageSseMapper {

    @Mapping(target = ".", source = "ticketMessage")
    TicketMessagePayload toPayload(TicketMessageView ticketMessage);
    
}
