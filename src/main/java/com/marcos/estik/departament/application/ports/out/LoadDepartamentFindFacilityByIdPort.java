package com.marcos.estik.departament.application.ports.out;

import java.util.UUID;

import com.marcos.estik.departament.application.view.DepartamentFacilityInfo;

public interface LoadDepartamentFindFacilityByIdPort {
    
    DepartamentFacilityInfo execute(UUID id);

}
