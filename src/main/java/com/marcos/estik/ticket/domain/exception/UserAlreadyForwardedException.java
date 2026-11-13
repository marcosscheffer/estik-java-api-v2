package com.marcos.estik.ticket.domain.exception;

public class UserAlreadyForwardedException extends RuntimeException {
    public UserAlreadyForwardedException(String message) {
        super(message);
    }
}