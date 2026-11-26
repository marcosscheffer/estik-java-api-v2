package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.CreateSolutionPostCommand;
import com.marcos.estik.solution.application.view.SolutionPostView;

public interface CreateSolutionPostPort {
    
    SolutionPostView execute(CreateSolutionPostCommand command, AuthenticatedUser principal);

}
