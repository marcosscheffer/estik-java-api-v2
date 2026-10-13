package com.marcos.estik.ticket.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsCommand;
import com.marcos.estik.ticket.domain.Ticket;

public interface SearchTicketsPort {
    PageResult<Ticket> execute(SearchTicketsCommand command);
}
