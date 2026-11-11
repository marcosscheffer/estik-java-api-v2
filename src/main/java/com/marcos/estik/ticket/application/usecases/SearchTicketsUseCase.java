package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsCommand;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketsUseCase implements SearchTicketsPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    public PageResult<TicketView> execute(SearchTicketsCommand command) {
        PageResult<Ticket> tickets = ticketRepositoryPort.searchTickets(
            command.title(),
            command.facilityId(),
            command.requesterId(),
            command.regulatorId(),
            command.forwardedId(),
            command.forwarded(),
            command.assigneeId(),
            command.priority(),
            command.type(),
            command.status(),
            command.page()
        );

        return tickets.map(ticket -> new TicketView(
                ticket,
                loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
                ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
                ticket.getForwardedId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getForwardedId()),
                ticket.getAssigneeId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
                loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
                loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId())
            )
        );
    }
}
