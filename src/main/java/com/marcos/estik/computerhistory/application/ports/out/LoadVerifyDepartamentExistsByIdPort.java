package com.marcos.estik.computerhistory.application.ports.out;

import java.util.UUID;

public interface LoadVerifyDepartamentExistsByIdPort {

    void existsById(UUID id);
    
}