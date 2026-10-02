package com.marcos.estik.user.domain.exception;

public class UsernameAlreadyUseException extends RuntimeException {
    public UsernameAlreadyUseException(String message) {
        super(message);
    }
}
