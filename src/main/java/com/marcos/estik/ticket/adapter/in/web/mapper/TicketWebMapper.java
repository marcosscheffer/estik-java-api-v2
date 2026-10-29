package com.marcos.estik.ticket.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.ticket.adapter.in.web.request.CreateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.request.ForwardRequest;
import com.marcos.estik.ticket.adapter.in.web.request.UpdateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.response.TicketResponse;
import com.marcos.estik.ticket.adapter.in.web.response.TicketSummaryResponse;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.command.ForwardCommand;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.application.view.TicketView;

@Mapper(componentModel = "spring")
public interface TicketWebMapper {
    
    CreateTicketCommand toCreateTicketCommand(CreateTicketRequest request);

    @Mapping(target = ".", source = "ticket")
    TicketResponse toResponse(TicketView ticket);

    @Mapping(target = ".", source = "ticket")
    TicketSummaryResponse toSummaryResponse(TicketView ticket);

    UpdateTicketCommand toUpdateTicketCommand(UpdateTicketRequest request);

    ForwardCommand toForwardCommand(ForwardRequest request);
}
