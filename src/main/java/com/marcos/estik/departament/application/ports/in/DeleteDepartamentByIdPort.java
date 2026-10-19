package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

public interface DeleteDepartamentByIdPort {
    void execute(UUID id);
}
