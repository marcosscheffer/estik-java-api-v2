package com.marcos.estik.solution.adapter.out.persistence.solutionresponse;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.domain.SolutionResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SolutionResponseRepositoryAdapter implements SolutionResponseRepositoryPort {

    private final JpaSolutionReponseRepository jpaSolutionReponseRepository;
    private final SolutionResponsePersistenceMapper solutionResponsePersistenceMapper;
    
    @Override
    public PageResult<SolutionResponse> searchSolutionResponses(UUID solutionPostId, PageQuery page) {
        Page<SolutionResponseEntity> solutionResponses = jpaSolutionReponseRepository.findBySolutionPostId(
            solutionPostId, 
            PageRequest.of(page.page(), page.size(), Sort.by("createdAt").ascending())
        );

        return new PageResult<SolutionResponse>(
            solutionResponses.getContent().stream().map(solutionResponsePersistenceMapper::toDomain).toList(),
            solutionResponses.getNumber(),
            solutionResponses.getSize(),
            solutionResponses.getTotalElements(),
            solutionResponses.getTotalPages()
        );
    }

    @Override
    public SolutionResponse findById(UUID id) {
        return solutionResponsePersistenceMapper.toDomain(
            jpaSolutionReponseRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public SolutionResponse save(SolutionResponse solutionResponse) {
        SolutionResponseEntity solutionResponseEntity = solutionResponsePersistenceMapper.toEntity(solutionResponse);
        SolutionResponseEntity solutionResponseEntitySaved = jpaSolutionReponseRepository.save(solutionResponseEntity);
        return solutionResponsePersistenceMapper.toDomain(solutionResponseEntitySaved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaSolutionReponseRepository.deleteById(id);
    }
    
}
