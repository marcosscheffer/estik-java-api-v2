package com.marcos.estik.solution.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.CreateSolutionPostCommand;
import com.marcos.estik.solution.application.port.in.CreateSolutionPostPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindSpecializationByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionStoreFilePort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionFileInfo;
import com.marcos.estik.solution.application.view.SolutionPostView;
import com.marcos.estik.solution.domain.SolutionPost;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateSolutionPostUseCase implements CreateSolutionPostPort {

    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionStoreFilePort loadSolutionStoreFilePort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;
    private final LoadSolutionFindSpecializationByIdPort loadSolutionFindSpecializationByIdPort;

    @Override
    @Transactional 
    public SolutionPostView execute(CreateSolutionPostCommand command, AuthenticatedUser principal) {
        SolutionFileInfo file = command.file() == null ? null : loadSolutionStoreFilePort.execute(command.file()); 
        SolutionPost solutionPost = SolutionPost.create(
            principal.id(), 
            principal.specializationId(), 
            command.title(), 
            command.description(), 
            file == null ? null : file.id()
        );
        solutionPostRepositoryPort.save(solutionPost);
        return new SolutionPostView(
            solutionPost,
            file == null ? null : file,
            loadSolutionFindUserByIdPort.execute(solutionPost.getAuthorId()),
            solutionPost.getSpecializationId() == null ? null : loadSolutionFindSpecializationByIdPort.execute(solutionPost.getSpecializationId())
        );
    }
}