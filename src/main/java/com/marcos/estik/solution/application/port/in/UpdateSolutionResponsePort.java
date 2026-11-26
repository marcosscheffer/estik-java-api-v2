package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.view.SolutionResponseView;

public interface UpdateSolutionResponsePort {
    
    SolutionResponseView execute(UUID id, String message, AuthenticatedUser principal);

}
