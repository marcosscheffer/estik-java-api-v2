package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.solution.application.view.SolutionUserInfo;

public interface LoadSolutionFindUserByIdPort {
    
    SolutionUserInfo execute(UUID userId);

}
