package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;

public interface DeleteSolutionResponseByIdPort {
    
    void execute(UUID id, AuthenticatedUser principal);

}
