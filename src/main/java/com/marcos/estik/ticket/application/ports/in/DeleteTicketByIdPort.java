package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

public interface DeleteTicketByIdPort {
    void execute(UUID id);
}
