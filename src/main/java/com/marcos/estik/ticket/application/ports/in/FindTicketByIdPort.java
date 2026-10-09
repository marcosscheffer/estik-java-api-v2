package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.ticket.domain.Ticket;

public interface FindTicketByIdPort {
    Ticket execute(UUID id);
}
