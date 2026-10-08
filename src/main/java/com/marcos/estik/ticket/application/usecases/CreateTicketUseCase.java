package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.out.LoadFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateTicketUseCase implements CreateTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadFacilityByIdPort loadFacilityByIdPort;

    @Override
    public Ticket execute(CreateTicketCommand command, UUID requesterId) {
        loadFacilityByIdPort.findById(command.facilityId());
        Ticket ticket = Ticket.create(
            command.title(),
            command.description(),
            requesterId, 
            command.facilityId(),
            command.priority()
        );

        ticketRepositoryPort.save(ticket);

        return ticket;
    }
}
