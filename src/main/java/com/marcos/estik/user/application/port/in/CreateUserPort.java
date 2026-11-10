package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.application.view.UserView;

public interface CreateUserPort {
    UserView execute(CreateUserCommand command);
}
