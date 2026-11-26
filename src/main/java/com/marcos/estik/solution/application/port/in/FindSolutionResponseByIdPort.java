package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.solution.application.view.SolutionResponseView;

public interface FindSolutionResponseByIdPort {
    
    SolutionResponseView execute(UUID id);

}
