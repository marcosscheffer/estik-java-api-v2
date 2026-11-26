package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.solution.application.port.in.FindSolutionPostByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSolutionPostByIdUseCase implements FindSolutionPostByIdPort {
    
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    
    @Override
    public SolutionPostView execute(UUID id) {
        SolutionPost solutionPost = solutionPostRepositoryPort.findById(id);
        return new SolutionPostView(
            solutionPost,
            solutionPost == null ? null : loadSolutionFindFileByidPort.execute(solutionPost.getFileId())
        );
    }
}
