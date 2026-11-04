package com.marcos.estik.chat.application.view;

import java.util.UUID;

public record ChatFileInfo(
    UUID id,
    String fileUrl,
    String fileType,
    Long fileSize,
    String fileName
) {
    
}
