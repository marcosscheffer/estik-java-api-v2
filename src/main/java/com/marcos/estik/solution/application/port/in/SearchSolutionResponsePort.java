package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.view.SolutionResponseView;

public interface SearchSolutionResponsePort {

    PageResult<SolutionResponseView> execute();
    
}
