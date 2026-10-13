package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.command.UpdateTicketCommand;
import com.marcos.estik.ticket.domain.Ticket;

public interface UpdateTicketPort {
    Ticket execute(UpdateTicketCommand command, UUID ticketId, AuthenticatedUser principal);
}
