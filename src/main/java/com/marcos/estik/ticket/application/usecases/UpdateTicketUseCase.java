package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.UpdateTicketPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateTicketUseCase implements UpdateTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    
    @Override
    public TicketView execute(UpdateTicketCommand command, UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        ticket.updateDetails(
            principal.id(),
            command.title(),
            command.description(), 
            command.ticketType()
        );
        ticketRepositoryPort.save(ticket);
        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            ticket.getForwardedId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getForwardedId()),
            ticket.getAssigneeId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId())
        );
    }
}
