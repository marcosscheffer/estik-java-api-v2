package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

public interface LoadComputerExistsFacilityByIdPort {
    Boolean existsById(UUID id);
}
