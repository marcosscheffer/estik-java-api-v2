package com.marcos.estik.departament.application.ports.in;

import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.domain.Departament;

public interface CreateDepartamentPort {
    
    Departament execute(CreateDepartamentCommand command);
}
