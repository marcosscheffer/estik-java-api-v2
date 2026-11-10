package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.user.application.view.UserView;

public interface ActiveUserByIdPort {
    UserView execute(UUID userId);
}
