package com.marcos.estik.chat.adapter.out.sse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record MessagePayload(
    UUID id,
    UUID chatId,
    String content,
    MessageSummaryPayload replyTo,
    Instant createdAt,
    Instant updatedAt,
    ParticipantSummaryPayload sender,
    List<AttachmentSummaryPayload> attachments
) {
    
}
