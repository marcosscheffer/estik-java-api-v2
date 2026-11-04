package com.marcos.estik.chat.adapter.out.sse;

import java.time.Instant;
import java.util.UUID;

public record MessageSummaryPayload(
    UUID id,
    String content,
    Instant createdAt,
    Instant updatedAt
) {
    
} 
