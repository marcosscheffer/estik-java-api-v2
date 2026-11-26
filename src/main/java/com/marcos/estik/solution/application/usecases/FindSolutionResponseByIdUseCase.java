package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.solution.application.port.in.FindSolutionResponseByIdPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionResponseView;
import com.marcos.estik.solution.domain.SolutionResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSolutionResponseByIdUseCase implements FindSolutionResponseByIdPort {

    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    
    @Override
    public SolutionResponseView execute(UUID id) {
        SolutionResponse solutionResponse = solutionResponseRepositoryPort.findById(id);
        return new SolutionResponseView(
            solutionResponse,
            solutionResponse.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(id)
        );
    }
}
