package com.marcos.estik.departament.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.CreateDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadDepartamentExistsFacilityByIdPort;
import com.marcos.estik.departament.domain.Departament;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateDepartamentUseCase implements CreateDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadDepartamentExistsFacilityByIdPort loadDepartamentExistsFacilityByIdPort;

    @Override
    public Departament execute(CreateDepartamentCommand command) {
        if (!loadDepartamentExistsFacilityByIdPort.existsById(command.facilityId())) {
            throw new EntityNotFoundException("Facility not found");
        }

        Departament departament = Departament.create(
            command.facilityId(), 
            command.name(), 
            command.code()
        );

        departamentRepositoryPort.save(departament);

        return departament;
    }
    
}
