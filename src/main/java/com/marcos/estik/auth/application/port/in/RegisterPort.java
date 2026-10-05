package com.marcos.estik.auth.application.port.in;

import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.auth.domain.UserAuth;

public interface RegisterPort {
    UserAuth execute(RegisterCommand command);
}