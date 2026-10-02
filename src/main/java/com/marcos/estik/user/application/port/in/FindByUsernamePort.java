package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.FindByUsernameCommand;
import com.marcos.estik.user.domain.User;

public interface FindByUsernamePort {
    User execute(FindByUsernameCommand command);
}
