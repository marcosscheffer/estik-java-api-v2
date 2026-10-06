package com.marcos.estik.auth.domain;

import java.time.Instant;
import java.util.UUID;

public record UserAuth(
    UUID id,
    String username,
    String email,
    String password,
    UUID facilityId,
    RoleEnum role,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {

}
