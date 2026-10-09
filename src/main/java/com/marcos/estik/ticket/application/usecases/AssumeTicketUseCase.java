package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.AssumeTicketCommand;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.VerifyUserExistsPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssumeTicketUseCase implements AssumeTicketPort {

    private final VerifyUserExistsPort verifyUserExistsPort;
    private final TicketRepositoryPort ticketRepositoryPort;
    
    @Override
    public Ticket execute(AssumeTicketCommand command) {
        verifyUserExistsPort.existsById(command.principal());
        Ticket ticket = ticketRepositoryPort.findTicketById(command.ticketId());

        ticket.setAssigneeId(command.principal());

        ticketRepositoryPort.save(ticket);
        return ticket;
    }
}
