package com.marcos.estik.user.application.port.in;

import java.util.UUID;

public interface ExistsUserByIdPort {
    Boolean execute(UUID id);
}
