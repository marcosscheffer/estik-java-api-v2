package com.marcos.estik.ticket.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.ticket.adapter.in.web.response.TicketMessageResponse;
import com.marcos.estik.ticket.application.view.TicketMessageView;

@Mapper(componentModel = "spring")
public interface TicketMessageWebMapper {

    @Mapping(target = ".", source = "ticketMessage")
    TicketMessageResponse toResponse(TicketMessageView ticketMessage);
    
}
