package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.in.SearchSolutionResponsePort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.application.view.SolutionResponseView;
import com.marcos.estik.solution.domain.SolutionResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSolutionResponseUseCase implements SearchSolutionResponsePort {
    
    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;
    private final LoadSolutionFindFileByidPort loadSolutionFindFileByidPort;
    private final LoadSolutionFindUserByIdPort loadSolutionFindUserByIdPort;

    @Override
    @Transactional(readOnly = true)
    public PageResult<SolutionResponseView> execute(UUID solutionPostId, PageQuery page) {
        PageResult<SolutionResponse> solutionResponses = solutionResponseRepositoryPort.searchSolutionResponses(solutionPostId, page);
        return solutionResponses.map(
            solutionResponse -> new SolutionResponseView(
                solutionResponse,
                solutionResponse.getFileId() == null ? null : loadSolutionFindFileByidPort.execute(solutionResponse.getFileId()),
                loadSolutionFindUserByIdPort.execute(solutionResponse.getAuthorId())
            )
        );
    }
}
