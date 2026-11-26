package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.CreateSolutionPostCommand;
import com.marcos.estik.solution.domain.SolutionPost;

public interface CreateSolutionPostPort {
    
    SolutionPost execute(CreateSolutionPostCommand command, AuthenticatedUser principal);

}
