package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.ports.in.ReopenTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ReopenTicketUseCase implements ReopenTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    
    @Override
    public Ticket execute(UUID id, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);
        ticket.reopen(principal.id());
        return ticketRepositoryPort.save(ticket);
    }
    
}
