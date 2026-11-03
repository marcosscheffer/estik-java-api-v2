package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record ChatAttachmentInfo(
    UUID id,
    UUID messageId,
    String fileName,
    String fileType,
    Long fileSize,
    String fileUrl,
    Instant createdAt
) {
    
}
