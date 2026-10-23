package com.marcos.estik.ticket.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.view.TicketUserInfo;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketFindUserByIdAdapter implements LoadTicketFindUserByIdPort {
    
    private final FindUserByIdPort findUserByIdPort;
    private final TicketUserMapper ticketUserMapper;
    
    @Override
    public TicketUserInfo execute(UUID id) {
        return ticketUserMapper.toUserInfo(findUserByIdPort.execute(id));
    }
}
