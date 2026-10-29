package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.user.domain.User;

public interface ChangeFunctionPort {
    
    User execute(UUID id, FunctionEnum function, AuthenticatedUser principal);

}
