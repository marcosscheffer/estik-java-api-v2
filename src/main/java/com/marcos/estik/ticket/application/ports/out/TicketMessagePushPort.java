package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.ticket.application.view.TicketMessageView;

public interface TicketMessagePushPort {

    void execute(TicketMessageView ticketMessage);
    
}