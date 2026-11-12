package com.marcos.estik.user.adapter.in.web.response;

import java.util.UUID;

public record UserResponseSummary(
    UUID id,
    String username,
    String email,
    UserSummaryRole role,
    UserSpecializationResponse specialization,
    Boolean active
) {
    
}
