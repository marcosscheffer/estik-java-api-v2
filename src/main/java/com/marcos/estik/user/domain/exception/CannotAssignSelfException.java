package com.marcos.estik.user.domain.exception;

public class CannotAssignSelfException extends RuntimeException {
    public CannotAssignSelfException(String message) {
        super(message);
    }
}
