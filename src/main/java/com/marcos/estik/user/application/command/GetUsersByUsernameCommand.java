package com.marcos.estik.user.application.command;

public record GetUsersByUsernameCommand(
    int page,
    int size
) {
    
}
