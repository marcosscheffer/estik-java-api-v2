package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.solution.application.view.SolutionFileInfo;

public interface LoadSolutionFindFileByidPort {
    
    SolutionFileInfo execute(UUID fileId);

}
