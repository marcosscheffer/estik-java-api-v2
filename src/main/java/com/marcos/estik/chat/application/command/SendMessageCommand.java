package com.marcos.estik.chat.application.command;

import java.util.UUID;

public record SendMessageCommand(
    UUID chatId,
    UUID senderId,
    UUID replyToId,
    String content
) {
    
}
