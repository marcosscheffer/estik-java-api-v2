package com.marcos.estik.chat.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record AttachmentResponse(
    UUID id,
    String fileName,
    String fileType,
    Long fileSize,
    String fileUrl,
    Instant createdAt
) {
    
}
