package com.marcos.estik.user.application.command;

public record CreateUserCommand(
    String email,
    String username,
    String password
) {
    
}
