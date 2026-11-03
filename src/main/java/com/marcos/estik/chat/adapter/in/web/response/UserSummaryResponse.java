package com.marcos.estik.chat.adapter.in.web.response;

import java.util.UUID;

public record UserSummaryResponse(
    UUID id,
    String username,
    String email
) {
    
}
