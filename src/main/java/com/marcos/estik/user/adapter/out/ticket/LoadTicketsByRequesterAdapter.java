package com.marcos.estik.user.adapter.out.ticket;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.in.FindTicketsByRequesterPort;
import com.marcos.estik.user.application.port.out.LoadUserTicketsByRequesterPort;
import com.marcos.estik.user.domain.UserTicket;

import lombok.RequiredArgsConstructor;


@Component 
@RequiredArgsConstructor 
public class LoadTicketsByRequesterAdapter implements LoadUserTicketsByRequesterPort {

    private final FindTicketsByRequesterPort findTicketsByRequesterPort;
    private final LoadTicketMapper loadTicketMapper;

    @Override
    public PageResult<UserTicket> findByRequester(UUID id, PageQuery page) {
        return findTicketsByRequesterPort.execute(id, page).map(loadTicketMapper::toDomain);
    }
    
}
