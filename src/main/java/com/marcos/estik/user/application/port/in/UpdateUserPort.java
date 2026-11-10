package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.application.view.UserView;

public interface UpdateUserPort {
    UserView execute(UUID userId, UpdateUserCommand command, AuthenticatedUser principal);
}
