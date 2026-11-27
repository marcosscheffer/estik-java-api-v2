package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.in.SearchSolutionPostPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindSpecializationByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSolutionPostUseCase implements SearchSolutionPostPort {
    
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;
    private final LoadSolutionFindSpecializationByIdPort loadSolutionFindSpecializationByIdPort;
    
    @Override
    @Transactional(readOnly = true)
    public PageResult<SolutionPostView> execute(String title, UUID specialiationId, PageQuery page) {
        PageResult<SolutionPost> solutionPosts = solutionPostRepositoryPort.searchSolutionPosts(
            title,
            specialiationId,
            page
         );
         return solutionPosts.map(solutionPost -> new SolutionPostView(
            solutionPost, 
            null,
            loadSolutionFindUserByIdPort.execute(solutionPost.getAuthorId()),
            solutionPost.getSpecializationId() == null ? null : loadSolutionFindSpecializationByIdPort.execute(solutionPost.getSpecializationId())
        ));
    }
}
