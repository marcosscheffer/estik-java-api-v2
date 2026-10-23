package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketFacilityInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateTicketUseCase implements CreateTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;

    @Override
    public TicketView execute(CreateTicketCommand command, UUID requesterId) {
        TicketFacilityInfo facilityInfo = loadTicketFindFacilityByIdPort.execute(command.facilityId());
        Ticket ticket = Ticket.create(
            command.title(),
            command.description(),
            command.ticketType(),
            requesterId, 
            command.facilityId(),
            command.priority()
        );

        ticketRepositoryPort.save(ticket);

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            facilityInfo
        );
    }
}
