package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.UpdateTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.VerifyUserExistsPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateTicketUseCase implements UpdateTicketPort {

    private final VerifyUserExistsPort verifyUserExistsPort;
    private final TicketRepositoryPort ticketRepositoryPort;
    @Override
    public Ticket execute(UpdateTicketCommand command, UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);

        if(principal.role().equals(RoleEnum.IT) || principal.role().equals(RoleEnum.ARCH)) {
            verifyUserExistsPort.existsById(command.assigneeId());
            ticket.setAssigneeId(command.assigneeId());
            ticket.setStatus(command.status());
        }

        ticket.setTitle(command.title());
        ticket.setDescription(command.description());
        ticket.setFacilityId(command.facilityId());
        ticket.setPriority(command.priority());

        ticketRepositoryPort.save(ticket);

        return ticket;
    }
}
