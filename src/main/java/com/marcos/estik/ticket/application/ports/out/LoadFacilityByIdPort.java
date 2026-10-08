package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface LoadFacilityByIdPort {
    UUID findById(UUID id);
}
