package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.ports.in.FindDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindDepartamentByIdUseCase implements FindDepartamentByIdPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;

    @Override
    public Departament execute(UUID id) {
        return departamentRepositoryPort.findById(id);
    }
    
}
