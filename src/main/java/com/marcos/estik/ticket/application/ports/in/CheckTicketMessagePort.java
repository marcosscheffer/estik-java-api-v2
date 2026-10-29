package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;

public interface CheckTicketMessagePort {
    void execute(UUID ticketId, AuthenticatedUser principal);
}
