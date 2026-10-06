package com.marcos.estik.auth.application.port.out;

import com.marcos.estik.auth.domain.UserAuth;

public interface LoadUserByUsernamePort {
    UserAuth findByUsername(String username);

}
