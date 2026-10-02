package com.marcos.estik.auth.domain.exception;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException() {
        super("User is inactive");
    }
    
}
