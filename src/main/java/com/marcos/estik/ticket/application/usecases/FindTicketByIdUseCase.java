package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.ports.in.FindTicketByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindTicketByIdUseCase implements FindTicketByIdPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;

    @Override
    public TicketView execute(UUID id) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);
        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            ticket.getForwardedId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getForwardedId()),
            ticket.getAssigneeId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId())
        );
    }
    
}
