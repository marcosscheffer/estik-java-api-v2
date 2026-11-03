package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChatMessageInfo(
    UUID id,
    UUID chatId,
    ChatParticipantInfo senderId,
    List<ChatAttachmentInfo> attachments,
    UUID replyToId,
    String content,
    Instant createdAt,
    Instant updatedAt
) {
    
}
