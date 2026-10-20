package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadExistsFacilityByIdPort;
import com.marcos.estik.ticket.domain.Ticket;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateTicketUseCase implements CreateTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadExistsFacilityByIdPort loadExistsFacilityByIdPort;

    @Override
    public Ticket execute(CreateTicketCommand command, UUID requesterId) {
        if(loadExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("Facility not found");
        }
        Ticket ticket = Ticket.create(
            command.title(),
            command.description(),
            command.ticketType(),
            requesterId, 
            command.facilityId(),
            command.priority()
        );

        ticketRepositoryPort.save(ticket);

        return ticket;
    }
}
