package com.marcos.estik.ticket.application.ports.in;

import com.marcos.estik.ticket.application.command.AssumeTicketCommand;
import com.marcos.estik.ticket.domain.Ticket;

public interface AssumeTicketPort {
    Ticket execute(AssumeTicketCommand command);
}
