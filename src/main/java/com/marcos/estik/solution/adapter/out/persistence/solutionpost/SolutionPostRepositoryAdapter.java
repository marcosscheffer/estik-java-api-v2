package com.marcos.estik.solution.adapter.out.persistence.solutionpost;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.domain.SolutionPost;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SolutionPostRepositoryAdapter implements SolutionPostRepositoryPort {
    
    private final JpaSolutionPostRepository jpaSolutionPostRepository;
    private final SolutionPostPersistenceMapper solutionPostPersistenceMapper;
    
    @Override
    public PageResult<SolutionPost> searchSolutionPosts(String title, UUID specializationId, PageQuery page) {
        Specification<SolutionPostEntity> spec = Specification.allOf(
            SolutionPostSpecification.titleContain(title),
            SolutionPostSpecification.specializationIs(specializationId)
        );

        Page<SolutionPostEntity> solutionPosts = jpaSolutionPostRepository.findAll(spec, PageRequest.of(page.page(), page.size()));
        return new PageResult<SolutionPost>(
            solutionPosts.getContent().stream().map(solutionPostPersistenceMapper::toDomain).toList(),
            solutionPosts.getNumber(),
            solutionPosts.getSize(),
            solutionPosts.getTotalElements(),
            solutionPosts.getTotalPages()
        );
    }

    @Override
    public SolutionPost findById(UUID id) {
        return solutionPostPersistenceMapper.toDomain(
            jpaSolutionPostRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public SolutionPost save(SolutionPost solutionPost) {
        SolutionPostEntity solutionPostEntity = solutionPostPersistenceMapper.toEntity(solutionPost);
        SolutionPostEntity solutionPostEntitySaved = jpaSolutionPostRepository.save(solutionPostEntity);
        return solutionPostPersistenceMapper.toDomain(solutionPostEntitySaved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaSolutionPostRepository.deleteById(id);
    }
    
}
