package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.UpdateTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateTicketUseCase implements UpdateTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    @Override
    public Ticket execute(UpdateTicketCommand command, UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        ticket.updateDetails(
            principal.id(),
            command.title(),
            command.description(), 
            command.priority(), 
            command.ticketType()
        );

        return ticketRepositoryPort.save(ticket);
    }
}
