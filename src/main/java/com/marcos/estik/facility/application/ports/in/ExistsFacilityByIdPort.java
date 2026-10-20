package com.marcos.estik.facility.application.ports.in;

import java.util.UUID;

public interface ExistsFacilityByIdPort {
    Boolean execute(UUID id);
}
