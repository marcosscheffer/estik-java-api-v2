package com.marcos.estik.departament.application.view;

import com.marcos.estik.departament.domain.Departament;

public record DepartamentView(
    Departament departament,
    DepartamentFacilityInfo facility
) {
    
}
