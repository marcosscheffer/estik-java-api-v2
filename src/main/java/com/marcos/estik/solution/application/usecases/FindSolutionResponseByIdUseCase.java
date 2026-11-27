package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.solution.application.port.in.FindSolutionResponseByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionResponseView;
import com.marcos.estik.solution.domain.SolutionResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSolutionResponseByIdUseCase implements FindSolutionResponseByIdPort {

    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;
    
    @Override
    @Transactional(readOnly = true)
    public SolutionResponseView execute(UUID id) {
        SolutionResponse solutionResponse = solutionResponseRepositoryPort.findById(id);
        return new SolutionResponseView(
            solutionResponse,
            solutionResponse.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(solutionResponse.getFileId()),
            loadSolutionFindUserByIdPort.execute(solutionResponse.getAuthorId())
        );
    }
}
