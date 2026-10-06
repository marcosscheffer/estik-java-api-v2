package com.marcos.estik.auth.application.port.out;

import java.util.UUID;

import com.marcos.estik.auth.domain.UserAuth;

public interface LoadUserByIdPort {
    UserAuth findById(UUID id);
}
