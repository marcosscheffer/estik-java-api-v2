package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.user.domain.User;

public interface AssignUserByIdPort {
    User execute(UUID userId, UUID principalId, UUID role);
}
