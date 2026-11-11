package com.marcos.estik.specialization.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;
import com.marcos.estik.specialization.domain.Specialization;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SpecializationRepositoryAdapter implements SpecializationRepositoryPort {

    private final JpaSpecializationRepository jpaSpecializationRepository;
    private final SpecializationPersistenceMapper specializationPersistenceMapper;

    @Override
    public Specialization save(Specialization specialization) {
        SpecializationEntity specializationEntity = specializationPersistenceMapper.toEntity(specialization);
        SpecializationEntity specializationEntitySaved = jpaSpecializationRepository.save(specializationEntity);
        return specializationPersistenceMapper.toDomain(specializationEntitySaved);
    }

    @Override
    public Specialization findById(UUID id) {
        SpecializationEntity specialization = jpaSpecializationRepository.findById(id)
            .orElseThrow(
                EntityNotFoundException::new 
            );
        
        return specializationPersistenceMapper.toDomain(specialization);
    }

    @Override
    public PageResult<Specialization> searchSpecialization(String name, PageQuery page) {
        Specification<SpecializationEntity> spec = Specification.allOf(
            SpecializationSpecification.nameContain(name)
        );
        Page<SpecializationEntity> specializations = jpaSpecializationRepository.findAll(
            spec, 
            PageRequest.of(
                page.page(), page.size(), Sort.by("createdAt")
            )
        );

        return new PageResult<Specialization>(
            specializations.stream().map(specializationPersistenceMapper::toDomain).toList(),
            specializations.getNumber(),
            specializations.getSize(),
            specializations.getTotalElements(),
            specializations.getTotalPages()
        );
    }

    @Override
    public void delete(UUID id) {
        jpaSpecializationRepository.deleteById(id);
    }
}
