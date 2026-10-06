package com.marcos.estik.auth.application.port.out;

import java.util.UUID;

import com.marcos.estik.auth.domain.NewUserAccount;

public interface CreateUserAccountPort {
    UUID createUser(NewUserAccount user);

}
