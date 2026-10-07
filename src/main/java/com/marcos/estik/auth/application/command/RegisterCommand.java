package com.marcos.estik.auth.application.command;

import java.util.UUID;

public record RegisterCommand(
    String email,
    String username,
    String password,
    UUID facility
) {
    
}
