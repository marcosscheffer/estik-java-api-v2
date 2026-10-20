package com.marcos.estik.departament.application.ports.out;

import java.util.UUID;

public interface LoadDepartamentExistsFacilityByIdPort {
    Boolean existsById(UUID id);
}
