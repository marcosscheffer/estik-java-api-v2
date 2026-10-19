package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.ports.in.DeleteTicketByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteTicketByIdUseCase implements DeleteTicketByIdPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public void execute(UUID id) {
        ticketRepositoryPort.deleteTicketById(id);
    }

    
}