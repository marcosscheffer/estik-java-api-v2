package com.marcos.estik.specialization.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.specialization.application.ports.in.SearchSpecializationPort;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;
import com.marcos.estik.specialization.domain.Specialization;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchSpecializationUseCase implements SearchSpecializationPort {

    private final SpecializationRepositoryPort specializationRepositoryPort;
    
    @Override
    public PageResult<Specialization> execute(String name, PageQuery page) {
        return specializationRepositoryPort.searchSpecialization(name, page);
    }
}
