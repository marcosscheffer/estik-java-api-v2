package com.marcos.estik.specialization.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.specialization.application.ports.in.DeleteSpecializationPort;
import com.marcos.estik.specialization.application.ports.out.SpecializationRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteSpecializationUseCase implements DeleteSpecializationPort {

    private final SpecializationRepositoryPort specializationRepositoryPort;

    @Override
    public void execute(UUID id) {
        specializationRepositoryPort.delete(id);
    }
}
