package com.marcos.estik.notification.domain.exception;

public class NotificationNotBelongException extends RuntimeException {
    public NotificationNotBelongException(String message) {
        super(message);
    }
}
