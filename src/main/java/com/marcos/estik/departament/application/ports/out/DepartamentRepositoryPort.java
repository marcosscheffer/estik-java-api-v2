package com.marcos.estik.departament.application.ports.out;

import java.util.UUID;

import com.marcos.estik.departament.domain.Departament;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface DepartamentRepositoryPort {

    Departament save(Departament departament);

    Departament findDepartamentById(UUID id);

    PageResult<Departament> searchDepartaments(String name, String code, 
        UUID facilityId, PageQuery page);

    Boolean existsDepartamentById(UUID id);
    
}
