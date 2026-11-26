package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.UpdateSolutionPostCommand;
import com.marcos.estik.solution.domain.SolutionPost;

public interface UpdateSolutionPostPort {
    
    SolutionPost execute(UpdateSolutionPostCommand command, AuthenticatedUser principal);

}
