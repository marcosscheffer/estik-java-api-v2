package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record ChatParticipantInfo(
    UUID id,
    UUID chatId,
    ChatUserInfo userId,
    Instant joinedAt
) {
    
}
