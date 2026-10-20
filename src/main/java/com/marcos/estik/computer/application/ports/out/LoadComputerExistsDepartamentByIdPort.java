package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

public interface LoadComputerExistsDepartamentByIdPort {
    
    Boolean existsById(UUID id);

}
