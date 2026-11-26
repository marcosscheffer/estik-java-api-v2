package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.solution.application.view.SolutionPostView;

public interface FindSolutionPostByIdPort {
    
    SolutionPostView execute(UUID id);

}
