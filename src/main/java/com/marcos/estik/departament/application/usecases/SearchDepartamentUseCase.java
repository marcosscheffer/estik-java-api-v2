package com.marcos.estik.departament.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.departament.application.command.SearchDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.SearchDepartamentPort;
import com.marcos.estik.departament.application.ports.out.DepartamentRepositoryPort;
import com.marcos.estik.departament.application.ports.out.LoadDepartamentFindFacilityByIdPort;
import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.departament.domain.Departament;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchDepartamentUseCase implements SearchDepartamentPort {

    private final DepartamentRepositoryPort departamentRepositoryPort;
    private final LoadDepartamentFindFacilityByIdPort loadDepartamentFindFacilityByIdPort;

    @Override
    public PageResult<DepartamentView> execute(SearchDepartamentCommand command) {
        PageResult<Departament> departaments =  departamentRepositoryPort.searchDepartaments(
            command.name(),
            command.code(),
            command.facilityId(),
            command.page()
        );

        return departaments.map(departament -> new DepartamentView(
            departament,
            loadDepartamentFindFacilityByIdPort.execute(departament.getFacilityId())
        ));
    }
}
