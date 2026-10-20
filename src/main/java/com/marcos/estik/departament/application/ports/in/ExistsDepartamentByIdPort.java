package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

public interface ExistsDepartamentByIdPort {
    Boolean execute(UUID id);
}
