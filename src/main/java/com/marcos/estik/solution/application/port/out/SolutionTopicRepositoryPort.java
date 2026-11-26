package com.marcos.estik.solution.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.domain.SolutionTopic;

public interface SolutionTopicRepositoryPort {
    
    PageResult<SolutionTopic> searchSolutionTopics();

    SolutionTopic findById(UUID id);

    SolutionTopic save(SolutionTopic solutionTopic);

}
