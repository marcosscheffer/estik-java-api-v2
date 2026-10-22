package com.marcos.estik.stock.domain.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
    
}
