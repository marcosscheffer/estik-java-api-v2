package com.marcos.estik.solution.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.in.SearchSolutionResponsePort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionResponseView;
import com.marcos.estik.solution.domain.SolutionResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSolutionResponseUseCase implements SearchSolutionResponsePort {
    
    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;

    @Override
    public PageResult<SolutionResponseView> execute() {
        PageResult<SolutionResponse> solutionResponses = solutionResponseRepositoryPort.searchSolutionResponses();
        return solutionResponses.map(
            solutionResponse -> new SolutionResponseView(
                solutionResponse,
                solutionResponse.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(solutionResponse.getFileId())
            )
        );
    }
}
