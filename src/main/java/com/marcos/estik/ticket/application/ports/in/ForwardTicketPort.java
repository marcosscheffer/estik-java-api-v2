package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.command.ForwardCommand;
import com.marcos.estik.ticket.application.view.TicketView;

public interface ForwardTicketPort {
    
    TicketView execute(UUID ticketId, ForwardCommand command, AuthenticatedUser principal);

}
