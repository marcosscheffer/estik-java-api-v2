package com.marcos.estik.solution.application.port.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.view.SolutionResponseView;

public interface SearchSolutionResponsePort {

    PageResult<SolutionResponseView> execute(UUID solutionPostId, PageQuery page);
    
}
