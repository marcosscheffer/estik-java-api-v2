package com.marcos.estik.computer.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ComputerRepositoryAdapter implements ComputerRepositoryPort {

    private final JpaComputerRepository jpaComputerRepository;
    private final ComputerPersistenceMapper computerPersistenceMapper;

    @Override
    public Computer save(Computer computer) {
        ComputerEntity computerEntity = computerPersistenceMapper.toEntity(computer);
        ComputerEntity computerSaved = jpaComputerRepository.save(computerEntity);
        return computerPersistenceMapper.toDomain(computerSaved);
    }

    @Override
    public Computer findComputerById(UUID id) {
        ComputerEntity computerEntity = jpaComputerRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        return computerPersistenceMapper.toDomain(computerEntity);

    }

    @Override
    public PageResult<Computer> searchComputer(
        UUID facilityId, 
        UUID departamentId, 
        String name, 
        PageQuery page
    ) {
        Specification<ComputerEntity> spec = Specification.allOf(
            ComputerSpecification.departamentIs(departamentId),
            ComputerSpecification.facilityIs(facilityId),
            ComputerSpecification.nameContain(name)
        );

        Page<ComputerEntity> computers =  jpaComputerRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size(), Sort.by("createdAt"))
        );

        return new PageResult<Computer>(
            computers.getContent().stream().map(computerPersistenceMapper::toDomain).toList(),
            computers.getNumber(),
            computers.getSize(),
            computers.getTotalElements(),
            computers.getTotalPages()
        );
    }

    @Override
    public void deleteComputerById(UUID id) {
        jpaComputerRepository.deleteById(id);
    }
}
