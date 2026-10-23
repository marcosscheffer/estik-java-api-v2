package com.marcos.estik.departament.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.CreateDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadDepartamentFindFacilityByIdPort;
import com.marcos.estik.departament.application.view.DepartamentFacilityInfo;
import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateDepartamentUseCase implements CreateDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadDepartamentFindFacilityByIdPort loadDepartamentFindFacilityByIdPort;

    @Override
    public DepartamentView execute(CreateDepartamentCommand command) {
        DepartamentFacilityInfo facilityInfo = loadDepartamentFindFacilityByIdPort.execute(command.facilityId());

        Departament departament = Departament.create(
            command.facilityId(), 
            command.name(), 
            command.code()
        );

        departamentRepositoryPort.save(departament);

        return new DepartamentView(
            departament,
            facilityInfo
        );
    }
}
