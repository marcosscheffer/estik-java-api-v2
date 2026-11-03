package com.marcos.estik.chat.application.command;

import java.util.UUID;

public record AttachFileMessageCommand(
    UUID messageId,
    String fileName,
    String fileType,
    Long fileSize,
    String fileLocation
) {
    
}
