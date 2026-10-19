package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.domain.User;

public interface UpdateUserPort {
    User execute(UUID userId, UpdateUserCommand command, AuthenticatedUser principal);
}
