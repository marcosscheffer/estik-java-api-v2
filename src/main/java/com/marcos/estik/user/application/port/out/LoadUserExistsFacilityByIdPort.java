package com.marcos.estik.user.application.port.out;

import java.util.UUID;

public interface LoadUserExistsFacilityByIdPort {
    Boolean existsById(UUID id);
}
