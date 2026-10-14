package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.domain.Departament;

public interface UpdateDepartamentPort {
    Departament execute(UUID departamentId, UpdateDepartamentCommand command);
}
