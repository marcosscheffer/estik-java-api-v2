package com.marcos.estik.solution.application.view;

import com.marcos.estik.solution.domain.SolutionResponse;

public record SolutionResponseView(
    SolutionResponse solutionResponse,
    SolutionFileInfo file,
    SolutionUserInfo author
) {
    
}
