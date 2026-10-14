package com.marcos.estik.departament.application.ports.in;

import java.util.UUID;

public interface VerifyDepartamentExistsByIdPort {
    void execute(UUID id);
}
