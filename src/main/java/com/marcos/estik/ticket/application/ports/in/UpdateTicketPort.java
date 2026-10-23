package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.application.view.TicketView;

public interface UpdateTicketPort {
    TicketView execute(UpdateTicketCommand command, UUID ticketId, AuthenticatedUser principal);
}
