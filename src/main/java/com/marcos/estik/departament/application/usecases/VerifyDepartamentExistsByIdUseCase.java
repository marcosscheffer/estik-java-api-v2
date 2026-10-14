package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.ports.in.VerifyDepartamentExistsByIdPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class VerifyDepartamentExistsByIdUseCase implements VerifyDepartamentExistsByIdPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;

    @Override
    public void execute(UUID id) {
        if (departamentRepositoryPort.existsDepartamentById(id)) {
            throw new EntityNotFoundException("departament not found"); 
        }
    }
}
