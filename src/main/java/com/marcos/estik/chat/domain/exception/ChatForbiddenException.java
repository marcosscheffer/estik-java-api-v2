package com.marcos.estik.chat.domain.exception;

public class ChatForbiddenException extends RuntimeException {
    public ChatForbiddenException(String message) {
        super(message);
    }
    
}
