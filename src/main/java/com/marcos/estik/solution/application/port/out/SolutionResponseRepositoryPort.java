package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.domain.SolutionResponse;

public interface SolutionResponseRepositoryPort {
    
    PageResult<SolutionResponse> searchSolutionResponses();

    SolutionResponse findById(UUID id);

    SolutionResponse save(SolutionResponse solutionTopic);

    void deleteById(UUID id);

}
