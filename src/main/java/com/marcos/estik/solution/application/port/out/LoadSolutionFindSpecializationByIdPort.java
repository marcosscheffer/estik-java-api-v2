package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.solution.application.view.SolutionSpecializationInfo;

public interface LoadSolutionFindSpecializationByIdPort {
    
    SolutionSpecializationInfo execute(UUID specializationId);

}
