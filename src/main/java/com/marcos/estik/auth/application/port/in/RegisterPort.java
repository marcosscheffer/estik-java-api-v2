package com.marcos.estik.auth.application.port.in;

import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.user.application.result.UserData;

public interface RegisterPort {
    UserData execute(RegisterCommand command);
}