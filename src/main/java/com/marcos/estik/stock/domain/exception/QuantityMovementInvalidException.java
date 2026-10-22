package com.marcos.estik.stock.domain.exception;

public class QuantityMovementInvalidException extends RuntimeException {
    public QuantityMovementInvalidException(String message) {
        super(message);
    }
}
