package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.view.TicketView;

public interface CreateTicketPort {
    TicketView execute(CreateTicketCommand command, UUID requesterId);
}
