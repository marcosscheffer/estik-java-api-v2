package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssumeTicketUseCase implements AssumeTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public Ticket execute(UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        ticket.setAssigneeId(principal.id());
        ticket.setStatus(StatusEnum.PROGRESS);
        ticketRepositoryPort.save(ticket);
        return ticket;
    }
}
