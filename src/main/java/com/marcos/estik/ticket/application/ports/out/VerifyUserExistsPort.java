package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface VerifyUserExistsPort {
    void existsById(UUID id);
}
