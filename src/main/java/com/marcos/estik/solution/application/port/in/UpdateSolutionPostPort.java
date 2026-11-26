package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.UpdateSolutionPostCommand;
import com.marcos.estik.solution.application.view.SolutionPostView;

public interface UpdateSolutionPostPort {
    
    SolutionPostView execute(UUID id, UpdateSolutionPostCommand command, AuthenticatedUser principal);

}
