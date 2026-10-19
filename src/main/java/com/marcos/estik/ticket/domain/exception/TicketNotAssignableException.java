package com.marcos.estik.ticket.domain.exception;

import java.util.UUID;

public class TicketNotAssignableException extends RuntimeException {
    public TicketNotAssignableException(UUID id) {
        super("impossible to sign on the ticket" + id);
    }
}
