package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.UpdateDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateDepartamentUseCase implements UpdateDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;

    @Override
    public Departament execute(UUID departamentId, UpdateDepartamentCommand command) {
        Departament departament = departamentRepositoryPort.findDepartamentById(departamentId);
        departament.setCode(command.code());
        departament.setName(command.name());
        departament.setFacilityId(command.facilityId());
        return departamentRepositoryPort.save(departament);

    }
}
