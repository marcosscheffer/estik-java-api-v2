package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.domain.User;

public interface CreateUserPort {
    User execute(CreateUserCommand command);
}
