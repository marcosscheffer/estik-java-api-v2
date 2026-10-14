package com.marcos.estik.departament.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.CreateDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadVerifyFacilityByIdPort;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateDepartamentUseCase implements CreateDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadVerifyFacilityByIdPort loadVerifyFacilityByIdPort;

    @Override
    public Departament execute(CreateDepartamentCommand command) {
        loadVerifyFacilityByIdPort.verifyById(command.facilityId());
        Departament departament = Departament.create(
            command.facilityId(), 
            command.name(), 
            command.code()
        );

        departamentRepositoryPort.save(departament);

        return departament;
    }
    
}
