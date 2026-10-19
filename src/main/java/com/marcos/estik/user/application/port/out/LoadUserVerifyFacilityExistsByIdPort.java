package com.marcos.estik.user.application.port.out;

import java.util.UUID;

public interface LoadUserVerifyFacilityExistsByIdPort {
    void existsById(UUID id);
}
