package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.view.SolutionPostView;

public interface SearchSolutionPostPort {
    
    PageResult<SolutionPostView> execute(String title, UUID specializationId);

}
