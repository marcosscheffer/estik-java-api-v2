package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record MessageInfo(
    UUID id,
    UUID chatId,
    String content,
    UUID replyToId,
    Instant createdAt,
    Instant updatedAt
) {
    
}
