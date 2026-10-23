package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

import com.marcos.estik.departament.application.view.DepartamentView;

public interface FindDepartamentByIdPort {
    
    DepartamentView execute(UUID id);
}
