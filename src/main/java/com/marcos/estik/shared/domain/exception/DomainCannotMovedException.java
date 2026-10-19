package com.marcos.estik.shared.domain.exception;

public class DomainCannotMovedException extends RuntimeException {
    public DomainCannotMovedException(String message) {
        super(message);
    }
}
