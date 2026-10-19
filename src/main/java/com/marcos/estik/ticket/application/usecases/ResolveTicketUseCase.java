package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.ports.in.ResolveTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ResolveTicketUseCase implements ResolveTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public Ticket execute(UUID id, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);
        ticket.resolve(principal.id());
        return ticketRepositoryPort.save(ticket);
    }
}
