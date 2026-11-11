package com.marcos.estik.specialization.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.specialization.domain.Specialization;

public interface SpecializationRepositoryPort {
    
    Specialization save(Specialization specialization);

    Specialization findById(UUID id);

    PageResult<Specialization> searchSpecialization(String name, PageQuery page);

    void delete(UUID id);

}
