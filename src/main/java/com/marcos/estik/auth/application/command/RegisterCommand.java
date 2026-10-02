package com.marcos.estik.auth.application.command;

public record RegisterCommand(
    String email,
    String username,
    String password
) {
    
}
