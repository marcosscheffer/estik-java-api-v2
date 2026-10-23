package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.UpdateDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadDepartamentFindFacilityByIdPort;
import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateDepartamentUseCase implements UpdateDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadDepartamentFindFacilityByIdPort loadDepartamentFindFacilityByIdPort;

    @Override
    public DepartamentView execute(UUID departamentId, UpdateDepartamentCommand command) {
        Departament departament = departamentRepositoryPort.findById(departamentId);
        departament.updateInfo(command.name(), command.code());
        departamentRepositoryPort.save(departament);

        return new DepartamentView(
            departament,
            loadDepartamentFindFacilityByIdPort.execute(departament.getFacilityId())
        );
    }
}
