package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.ports.in.DeleteDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteDepartamentUseCase implements DeleteDepartamentByIdPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;

    @Override
    public void execute(UUID id) {
        departamentRepositoryPort.deleteById(id);
    }
}
