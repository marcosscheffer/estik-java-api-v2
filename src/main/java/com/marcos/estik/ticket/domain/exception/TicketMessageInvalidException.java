package com.marcos.estik.ticket.domain.exception;

public class TicketMessageInvalidException extends RuntimeException {
    public TicketMessageInvalidException(String message) {
        super(message);
    }
}