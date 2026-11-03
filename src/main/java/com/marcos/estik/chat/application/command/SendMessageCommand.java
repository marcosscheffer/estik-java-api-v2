package com.marcos.estik.chat.application.command;

import java.util.List;
import java.util.UUID;

public record SendMessageCommand(
    UUID replyToId,
    String content,
    List<FileUpload> files
) {
    
}
