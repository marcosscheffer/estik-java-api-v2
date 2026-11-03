package com.marcos.estik.chat.application.view;

import java.util.UUID;

public record ChatUserInfo(
    UUID id,
    UUID participantId,
    String username,
    String email
) {
    
}
