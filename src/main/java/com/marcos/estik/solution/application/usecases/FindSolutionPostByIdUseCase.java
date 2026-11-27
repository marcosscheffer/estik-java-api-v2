package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.solution.application.port.in.FindSolutionPostByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindSpecializationByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSolutionPostByIdUseCase implements FindSolutionPostByIdPort {
    
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;
    private final LoadSolutionFindSpecializationByIdPort loadSolutionFindSpecializationByIdPort;
    
    @Override
    @Transactional(readOnly = true)
    public SolutionPostView execute(UUID id) {
        SolutionPost solutionPost = solutionPostRepositoryPort.findById(id);
        return new SolutionPostView(
            solutionPost,
            solutionPost.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(solutionPost.getFileId()),
            loadSolutionFindUserByIdPort.execute(solutionPost.getAuthorId()),
            solutionPost.getSpecializationId() == null ? null : loadSolutionFindSpecializationByIdPort.execute(solutionPost.getSpecializationId())
        );
    }
}
