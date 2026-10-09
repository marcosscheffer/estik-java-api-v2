package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.in.FindTicketsByRequesterPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindTicketsByRequesterUseCase implements FindTicketsByRequesterPort {

    private final TicketRepositoryPort ticketRepositoryPort;

    @Override
    public PageResult<Ticket> execute(UUID id, PageQuery page) {
        return ticketRepositoryPort.findTicketsByRequester(id, page);
    }
    
}
