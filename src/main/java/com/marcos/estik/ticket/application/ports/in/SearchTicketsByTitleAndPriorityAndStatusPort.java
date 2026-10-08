package com.marcos.estik.ticket.application.ports.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsByTitleAndPriorityAndStatusCommand;
import com.marcos.estik.ticket.domain.Ticket;

public interface SearchTicketsByTitleAndPriorityAndStatusPort {
    PageResult<Ticket> execute(SearchTicketsByTitleAndPriorityAndStatusCommand command);
}
