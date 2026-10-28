package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.ports.in.CheckTicketMessagePort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CheckTicketMessageUseCase implements CheckTicketMessagePort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    
    @Override
    @Transactional(readOnly = true)
    public void execute(UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);

        if(ticket.canAccessChat(principal.id())) {
            throw new TicketOwnerShipException("you cannot access this ticket chat");
        }
    }
}
