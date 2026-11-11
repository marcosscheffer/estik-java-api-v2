package com.marcos.estik.specialization.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.specialization.application.ports.in.CreateSpecializationPort;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;
import com.marcos.estik.specialization.domain.Specialization;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateSpecializationUseCase implements CreateSpecializationPort {

    private final SpecializationRepositoryPort specializationRepositoryPort;

    @Override
    public Specialization execute(String name) {
        Specialization specialization = Specialization.create(name);
        specializationRepositoryPort.save(specialization);
        return specialization;
    }
}
