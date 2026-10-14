package com.marcos.estik.computerhistory.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.computerhistory.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computerhistory.domain.ComputerHistory;
import com.marcos.estik.computerhistory.domain.DirectionEnum;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ComputerHistoryRepositoryAdapter implements ComputerHistoryRepositoryPort {

    private final JpaComputerHistoryRepository jpaComputerHistoryRepository;
    private final ComputerHistoryPersistenceMapper computerHistoryPersistenceMapper;

    @Override
    public ComputerHistory save(ComputerHistory computerHistory) {
        ComputerHistoryEntity computerHistoryEntity = computerHistoryPersistenceMapper.toEntity(computerHistory);
        jpaComputerHistoryRepository.save(computerHistoryEntity);
        return computerHistoryPersistenceMapper.toDomain(computerHistoryEntity);
    }

    @Override
    public ComputerHistory findComputerHistoryById(UUID id) {
        ComputerHistoryEntity computerHistoryEntity = jpaComputerHistoryRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new );

        return computerHistoryPersistenceMapper.toDomain(computerHistoryEntity);
    }

    @Override
    public PageResult<ComputerHistory> SearchComputerHistory(
        DirectionEnum direction, 
        UUID computerId,
        UUID departamentId,
        PageQuery page
    ) {
        Specification<ComputerHistoryEntity> spec = Specification.allOf(
            ComputerHistorySpecification.computerIs(computerId),
            ComputerHistorySpecification.departamentIs(departamentId),
            ComputerHistorySpecification.directionIs(direction)
        );

        Page<ComputerHistoryEntity> computerHistories = jpaComputerHistoryRepository
            .findAll(spec, PageRequest.of(page.page(), page.size(), Sort.by("createdAt")));
        
        return new PageResult<ComputerHistory>(
            computerHistories.getContent().stream().map(computerHistoryPersistenceMapper::toDomain).toList(),
            computerHistories.getNumber(),
            computerHistories.getSize(),
            computerHistories.getTotalElements(),
            computerHistories.getTotalPages()
        );
    }
}
