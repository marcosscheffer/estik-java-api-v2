package com.marcos.estik.departament.application.ports.in;

import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.view.DepartamentView;

public interface CreateDepartamentPort {
    
    DepartamentView execute(CreateDepartamentCommand command);
}
