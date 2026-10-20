package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.ports.in.ExistsDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsDepartamentByIdUseCase implements ExistsDepartamentByIdPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;

    @Override
    public Boolean execute(UUID id) {
        return departamentRepositoryPort.existsById(id);
    }
}
