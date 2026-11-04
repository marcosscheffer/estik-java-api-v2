package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record ChatAttachmentInfo(
    UUID id,
    UUID messageId,
    ChatFileInfo file,
    Instant createdAt
) {
    
}
