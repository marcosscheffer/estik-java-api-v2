package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.user.application.view.UserView;

public interface AssignUserByIdPort {
    UserView execute(UUID userId, UUID principalId, UUID role);
}
