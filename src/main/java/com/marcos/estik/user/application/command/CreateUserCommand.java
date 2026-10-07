package com.marcos.estik.user.application.command;

import java.util.UUID;

public record CreateUserCommand(
    String email,
    String username,
    String password,
    UUID facility
) {
    
}
