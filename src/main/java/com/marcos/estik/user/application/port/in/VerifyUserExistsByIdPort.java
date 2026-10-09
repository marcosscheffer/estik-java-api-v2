package com.marcos.estik.user.application.port.in;

import java.util.UUID;

public interface VerifyUserExistsByIdPort {
    void execute(UUID id);
}
