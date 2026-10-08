package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsByTitleAndPriorityAndStatusCommand;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsByTitleAndPriorityAndStatusPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketByTitleAndPriorityAndStatusUseCase implements SearchTicketsByTitleAndPriorityAndStatusPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public PageResult<Ticket> execute(SearchTicketsByTitleAndPriorityAndStatusCommand command) {
        if (command.priority() != null) {
            return ticketRepositoryPort.SearchTicketsByPriority(
                command.title(), 
                command.priority(), 
                command.page()
            );
        }

        if (command.status() != null) {
            return ticketRepositoryPort.SearchTicketsByStatus(
                command.title(),
                command.status(),
                command.page()
            );
        }

        return ticketRepositoryPort.SearchTickets(command.title(), command.page());
    }
    
}
