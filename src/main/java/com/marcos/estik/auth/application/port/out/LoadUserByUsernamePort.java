package com.marcos.estik.auth.application.port.out;

import com.marcos.estik.auth.domain.UserCredentials;

public interface LoadUserByUsernamePort {
    UserCredentials findByUsername(String username);

}
