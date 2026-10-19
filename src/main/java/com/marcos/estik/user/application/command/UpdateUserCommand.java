package com.marcos.estik.user.application.command;

public record UpdateUserCommand(
    String email,
    String username
) {

}
