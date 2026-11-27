package com.marcos.estik.solution.application.view;

import com.marcos.estik.solution.domain.SolutionPost;

public record SolutionPostView(
    SolutionPost solutionPost,
    SolutionFileInfo file,
    SolutionUserInfo author,
    SolutionSpecializationInfo specialization
) {
    
} 
