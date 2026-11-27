package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.UpdateSolutionPostCommand;
import com.marcos.estik.solution.application.port.in.UpdateSolutionPostPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindSpecializationByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateSolutionPostUseCase implements UpdateSolutionPostPort {

    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;
    private final LoadSolutionFindSpecializationByIdPort loadSolutionFindSpecializationByIdPort;
    
    @Override
    @Transactional 
    public SolutionPostView execute(UUID id, UpdateSolutionPostCommand command, AuthenticatedUser principal) {
        SolutionPost solutionPost = solutionPostRepositoryPort.findById(id);
        solutionPost.update(principal.id(), command.title(), command.description());
        solutionPostRepositoryPort.save(solutionPost);
        return new SolutionPostView(
            solutionPost,
            solutionPost.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(solutionPost.getFileId()),
            loadSolutionFindUserByIdPort.execute(solutionPost.getAuthorId()),
            solutionPost.getSpecializationId() == null ? null : loadSolutionFindSpecializationByIdPort.execute(solutionPost.getSpecializationId())
        );
    }
}
