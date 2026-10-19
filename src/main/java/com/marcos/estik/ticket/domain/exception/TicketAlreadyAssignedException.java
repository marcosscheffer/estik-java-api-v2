package com.marcos.estik.ticket.domain.exception;

import java.util.UUID;

public class TicketAlreadyAssignedException extends RuntimeException {
    public TicketAlreadyAssignedException(UUID id) {
        super("ticket already assigned " + id);
    }
}
