package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.ticket.application.view.TicketUserInfo;

public interface LoadTicketFindUserByIdPort {
    
    TicketUserInfo execute(UUID id);

}
