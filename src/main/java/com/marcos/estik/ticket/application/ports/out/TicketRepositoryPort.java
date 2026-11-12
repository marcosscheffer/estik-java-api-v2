package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsFilter;
import com.marcos.estik.ticket.domain.Ticket;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    PageResult<Ticket> searchTickets(
        SearchTicketsFilter filter
    );

    Ticket findTicketById(UUID id);

    void deleteTicketById(UUID id);

}
