package com.marcos.estik.chat.adapter.out.ticket;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.application.view.ChatTicketInfo;
import com.marcos.estik.ticket.application.view.TicketView;

@Mapper(componentModel = "spring")
public interface ChatTicketMapper {
    
    @Mapping(target = "specializationId", source = "specialization.id")
    @Mapping(target = "id", source = "ticket.id")
    ChatTicketInfo toInfo(TicketView ticket);

}
