package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.application.view.DepartamentView;

public interface UpdateDepartamentPort {

    DepartamentView execute(UUID departamentId, UpdateDepartamentCommand command);
    
}
