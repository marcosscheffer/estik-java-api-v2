package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsCommand;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketsUseCase implements SearchTicketsPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public PageResult<Ticket> execute(SearchTicketsCommand command) {
        return ticketRepositoryPort.searchTickets(
            command.title(),
            command.facilityId(),
            command.requesterId(),
            command.assigneeId(),
            command.priority(),
            command.status(),
            command.page()
        );
    }
}
