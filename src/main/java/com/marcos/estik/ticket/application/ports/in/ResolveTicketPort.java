package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.domain.Ticket;

public interface ResolveTicketPort {
    Ticket execute(UUID id, AuthenticatedUser principal);
}
