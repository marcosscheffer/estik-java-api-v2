package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.domain.Ticket;

public interface CreateTicketPort {
    Ticket execute(CreateTicketCommand command, UUID requesterId);
}
