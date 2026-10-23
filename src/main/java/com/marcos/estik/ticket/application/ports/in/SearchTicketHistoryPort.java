package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketHistoriesCommand;
import com.marcos.estik.ticket.domain.TicketHistory;

public interface SearchTicketHistoryPort {
    
    PageResult<TicketHistory> execute(UUID id, SearchTicketHistoriesCommand command);

}
