package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.ports.in.FindTicketByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindTicketByIdUseCase implements FindTicketByIdPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public Ticket execute(UUID id) {
        return ticketRepositoryPort.findTicketById(id);
    }
    
}
