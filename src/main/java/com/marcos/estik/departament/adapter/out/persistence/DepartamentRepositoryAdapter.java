package com.marcos.estik.departament.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.domain.Departament;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class DepartamentRepositoryAdapter implements DepartamentRepositoryPort {

    private final JpaDepartamentRepository jpaDepartamentRepository;
    private final DepartamentPersistenceMapper departamentPersistenceMapper;

    @Override
    public Departament save(Departament departament) {
        DepartamentEntity departamentEntity = departamentPersistenceMapper.toEntity(departament);
        DepartamentEntity departamentSaved = jpaDepartamentRepository.save(departamentEntity);
        return departamentPersistenceMapper.toDomain(departamentSaved);
    }

    @Override
    public Departament findById(UUID id) {
        return departamentPersistenceMapper.toDomain(
            jpaDepartamentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public PageResult<Departament> searchDepartaments(String name, String code, UUID facilityId, PageQuery page) {
        Specification<DepartamentEntity> spec = Specification.allOf(
            DepartamentSpecification.facilityIs(facilityId),
            DepartamentSpecification.nameContain(name),
            DepartamentSpecification.codeContain(code)
        );

        Page<DepartamentEntity> departaments = jpaDepartamentRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size(), Sort.by("createdAt"))
        );

        return new PageResult<Departament>(
            departaments.getContent().stream().map(departamentPersistenceMapper::toDomain).toList(),
            departaments.getNumber(),
            departaments.getSize(),
            departaments.getTotalElements(),
            departaments.getTotalPages()
        );
    }

    @Override
    public void deleteById(UUID id) {
        jpaDepartamentRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaDepartamentRepository.existsById(id);
    }
    
}
