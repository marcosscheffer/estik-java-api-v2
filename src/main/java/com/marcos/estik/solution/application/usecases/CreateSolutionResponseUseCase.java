package com.marcos.estik.solution.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.solution.application.command.CreateSolutionResponseCommand;
import com.marcos.estik.solution.application.port.in.CreateSolutionResponsePort;
import com.marcos.estik.solution.application.port.out.LoadSolutionStoreFilePort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionFileInfo;
import com.marcos.estik.solution.application.view.SolutionResponseView;
import com.marcos.estik.solution.domain.SolutionPost;
import com.marcos.estik.solution.domain.SolutionResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateSolutionResponseUseCase implements CreateSolutionResponsePort {
    
    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;
    private final LoadSolutionStoreFilePort loadSolutionStoreFilePort;

    @Override
    @Transactional 
    public SolutionResponseView execute(CreateSolutionResponseCommand command, AuthenticatedUser principal) {
        SolutionPost solutionPost = solutionPostRepositoryPort.findById(command.solutionPostId());
        SolutionFileInfo file = command.file() == null ? null : loadSolutionStoreFilePort.execute(command.file());
        SolutionResponse solutionResponse = SolutionResponse.create(
            principal.id(), 
            solutionPost.getId(), 
            command.message(), 
            file == null ? null : file.id()
        );
        solutionResponseRepositoryPort.save(solutionResponse);
        return new SolutionResponseView(
            solutionResponse,
            file
        );
    } 
}
