package com.marcos.estik.auth.application.port.in;

import com.marcos.estik.auth.application.command.LoginCommand;
import com.marcos.estik.auth.application.result.AuthTokens;

public interface LoginPort {
    AuthTokens execute(LoginCommand command);
}
