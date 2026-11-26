package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.CreateSolutionResponseCommand;
import com.marcos.estik.solution.application.view.SolutionResponseView;

public interface CreateSolutionResponsePort {
    
    SolutionResponseView execute(CreateSolutionResponseCommand command, AuthenticatedUser principal);

}
