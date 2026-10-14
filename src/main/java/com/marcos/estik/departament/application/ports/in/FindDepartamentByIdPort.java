package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

import com.marcos.estik.departament.domain.Departament;

public interface FindDepartamentByIdPort {
    
    Departament execute(UUID id);
}
