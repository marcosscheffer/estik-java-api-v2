package com.marcos.estik.computerhistory.application.ports.out;

import java.util.UUID;

public interface LoadComputerHistoryExistsDepartamentByIdPort {

    Boolean existsById(UUID id);
    
}