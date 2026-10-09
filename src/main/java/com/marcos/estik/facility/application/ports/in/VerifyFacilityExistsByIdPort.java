package com.marcos.estik.facility.application.ports.in;

import java.util.UUID;

public interface VerifyFacilityExistsByIdPort {
    void execute(UUID id);
}
