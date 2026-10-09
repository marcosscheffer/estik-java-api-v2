package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface VerifyFacilityExistsPort {
    void existsById(UUID id);
}
