package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.port.in.FindTicketsByUserPort;
import com.marcos.estik.user.application.port.out.LoadUserTicketsByRequesterPort;
import com.marcos.estik.user.domain.UserTicket;

import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class FindTicketsByUserUseCase implements FindTicketsByUserPort {

    private final LoadUserTicketsByRequesterPort loadUserTicketsByRequesterPort;

    @Override
    public PageResult<UserTicket> execute(UUID id, PageQuery page) {
        return loadUserTicketsByRequesterPort.findByRequester(id, page);

    }
}
