package com.marcos.estik.facility.adapter.in.web.response;

import java.util.UUID;

public record FacilityUsersResponse(
    UUID userId,
    String username,
    String email
) {
    
}
