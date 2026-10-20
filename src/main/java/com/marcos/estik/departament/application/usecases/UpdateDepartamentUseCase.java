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
        Departament departament = departamentRepositoryPort.findById(departamentId);
        departament.updateInfo(command.name(), command.code());
        return departamentRepositoryPort.save(departament);
    }
}
