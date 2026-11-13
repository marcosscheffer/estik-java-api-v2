package com.marcos.estik.ticket.domain.exception;

public class UserAlreadyAssigneedException extends RuntimeException {
    public UserAlreadyAssigneedException(String message) {
        super(message);
    }
}
