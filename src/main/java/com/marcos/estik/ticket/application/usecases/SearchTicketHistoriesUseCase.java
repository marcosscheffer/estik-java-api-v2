package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketHistoriesCommand;
import com.marcos.estik.ticket.application.ports.in.SearchTicketHistoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.domain.TicketHistory;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketHistoriesUseCase implements SearchTicketHistoryPort {

    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;

    @Override
    public PageResult<TicketHistory> execute(UUID id, SearchTicketHistoriesCommand command) {
        return ticketHistoryRepositoryPort.searchTicketHistory(
            id, command.status(), command.type(), command.page());
    }


    
}
