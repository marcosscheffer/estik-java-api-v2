package com.marcos.estik.user.application.port.in;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.user.domain.User;

public interface FindUserByRolePort {

    List<User> execute(UUID role);
    
}
