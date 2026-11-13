package com.marcos.estik.specialization.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.specialization.application.ports.in.UpdateSpecializationPort;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;
import com.marcos.estik.specialization.domain.Specialization;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateSpecializationUseCase implements UpdateSpecializationPort {
    
    private final SpecializationRepositoryPort specializationRepositoryPort;
    
    @Override
    public Specialization execute(UUID id, String name) {
        Specialization specialization = specializationRepositoryPort.findById(id);
        specialization.updateName(name);
        specializationRepositoryPort.save(specialization);
        return specialization;
    }
}
