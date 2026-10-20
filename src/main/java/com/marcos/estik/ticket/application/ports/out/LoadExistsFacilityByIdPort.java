package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface LoadExistsFacilityByIdPort {
    Boolean existsById(UUID id);
}
