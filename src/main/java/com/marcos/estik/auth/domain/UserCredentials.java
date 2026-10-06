package com.marcos.estik.auth.domain;

import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;

public record UserCredentials(
    UUID id,
    String username,
    String email,
    String password,
    RoleEnum role,
    Boolean active
) {

}
