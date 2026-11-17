package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.ticket.application.view.TicketView;

public interface FindTicketByChatIdPort {
    
    TicketView execute(UUID chatId);

}
