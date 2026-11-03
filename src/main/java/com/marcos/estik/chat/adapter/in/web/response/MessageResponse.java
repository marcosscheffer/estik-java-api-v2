package com.marcos.estik.chat.adapter.in.web.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record MessageResponse(
    UUID id,
    ParticipantResponse senderId,
    List<AttachmentResponse> attachments,
    UUID replyToId,
    String content,
    Instant createdAt,
    Instant updatedAt
) {
}
