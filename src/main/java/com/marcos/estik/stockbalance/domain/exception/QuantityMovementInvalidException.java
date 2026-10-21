package com.marcos.estik.stockbalance.domain.exception;

public class QuantityMovementInvalidException extends RuntimeException {
    public QuantityMovementInvalidException(String message) {
        super(message);
    }
}
