package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.domain.User;

public interface FindByUsernamePort {
    User execute(String command);
}
