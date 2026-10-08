package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.domain.User;

public interface UpdateUserPort {
    User execute(UpdateUserCommand command);
}
