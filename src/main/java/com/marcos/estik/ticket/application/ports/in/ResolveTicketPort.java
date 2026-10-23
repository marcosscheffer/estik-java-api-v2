package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.application.view.TicketView;

public interface ResolveTicketPort {
    TicketView execute(UUID id, AuthenticatedUser principal);
}
