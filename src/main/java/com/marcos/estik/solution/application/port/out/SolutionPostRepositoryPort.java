package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.domain.SolutionPost;

public interface SolutionPostRepositoryPort {
    
    PageResult<SolutionPost> searchSolutionPosts(String title, UUID specializationId, PageQuery page);

    SolutionPost findById(UUID id);

    SolutionPost save(SolutionPost solutionPost);

    void deleteById(UUID id);

}
