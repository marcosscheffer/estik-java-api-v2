package com.marcos.estik.chat.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record MessageSummaryResponse(
    UUID id,
    String content,
    Instant createdAt,
    Instant updatedAt
) {
    
}
