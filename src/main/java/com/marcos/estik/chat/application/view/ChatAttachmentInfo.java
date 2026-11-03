package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record ChatAttachmentInfo(
    UUID id,
    String fileName,
    String fileType,
    Long fileSize,
    String fileUrl,
    Instant createdAt
) {
    
}
