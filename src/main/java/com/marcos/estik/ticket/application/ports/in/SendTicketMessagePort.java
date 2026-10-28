package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.view.TicketMessageView;

public interface SendTicketMessagePort {

    TicketMessageView execute(UUID ticketId, String body, AuthenticatedUser principal);
    
}
