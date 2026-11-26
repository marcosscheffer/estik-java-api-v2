package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.solution.domain.SolutionPost;

public interface FindSolutionPostByIdPort {
    
    SolutionPost execute(UUID id);

}
