package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.in.SearchSolutionPostPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSolutionPostUseCase implements SearchSolutionPostPort {
    
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    
    @Override
    @Transactional(readOnly = true)
    public PageResult<SolutionPostView> execute(String title, UUID specialiationId) {
        PageResult<SolutionPost> solutionPosts = solutionPostRepositoryPort.searchSolutionPosts(
            title,
            specialiationId
         );
         return solutionPosts.map(solutionPost -> new SolutionPostView(solutionPost, null));
    }
}
