package com.marcos.estik.solution.application.port.in;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.domain.SolutionPost;

public interface SearchSolutionPostPort {
    
    PageResult<SolutionPost> execute(String title, AuthenticatedUser principal);

}
