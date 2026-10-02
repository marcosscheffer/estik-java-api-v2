package com.marcos.estik.user.application.result;

import java.time.Instant;
import java.util.UUID;

public record UserData(
    UUID id,
    String username,
    String email,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
