package com.marcos.estik.chat.application.view;

import java.time.Instant;
import java.util.UUID;

public record ChatParticipantInfo(
    UUID id,
    ChatUserInfo userId,
    Instant joinedAt
) {
    
}
