package com.marcos.estik.departament.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.ports.in.FindDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadDepartamentFindFacilityByIdPort;
import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.departament.domain.Departament;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindDepartamentByIdUseCase implements FindDepartamentByIdPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadDepartamentFindFacilityByIdPort loadDepartamentFindFacilityByIdPort;

    @Override
    public DepartamentView execute(UUID id) {
        Departament departament = departamentRepositoryPort.findById(id);
        return new DepartamentView(
            departament,
            loadDepartamentFindFacilityByIdPort.execute(departament.getFacilityId())
        );
    }
}
