package com.marcos.estik.specialization.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.specialization.application.ports.in.FindSpecializationByIdPort;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;
import com.marcos.estik.specialization.domain.Specialization;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindSpecializationByIdUseCase implements FindSpecializationByIdPort {

    private final SpecializationRepositoryPort specializationRepositoryPort;

    @Override
    public Specialization execute(UUID id) {
        return specializationRepositoryPort.findById(id);
    }


    
}
