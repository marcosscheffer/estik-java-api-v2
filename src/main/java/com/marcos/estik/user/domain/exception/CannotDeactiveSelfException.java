package com.marcos.estik.user.domain.exception;

public class CannotDeactiveSelfException extends RuntimeException {
    public CannotDeactiveSelfException(String message) {
        super(message);
    }
    
}
