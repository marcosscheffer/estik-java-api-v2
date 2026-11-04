package com.marcos.estik.chat.adapter.out.sse;

import java.util.UUID;

public record UserSummaryPayload(
    UUID id,
    String username,
    String email
) {
    
}
