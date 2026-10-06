package com.marcos.estik.user.application.command;

public record SearchByIdCommand(
    String username,
    int page,
    int size
) {
}
