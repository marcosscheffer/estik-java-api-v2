package com.marcos.estik.ticket.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.adapter.in.web.request.CreateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.request.UpdateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.response.TicketResponse;
import com.marcos.estik.ticket.adapter.in.web.response.TicketSummaryResponse;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.domain.Ticket;

@Mapper(componentModel = "spring")
public interface TicketWebMapper {
    
    CreateTicketCommand toCreateTicketCommand(CreateTicketRequest request);

    TicketResponse toResponse(Ticket ticket);

    TicketSummaryResponse toSummaryResponse(Ticket ticket);

    UpdateTicketCommand toUpdateTicketCommand(UpdateTicketRequest request);
}
