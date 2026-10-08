package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.ticket.domain.Ticket;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);
}
