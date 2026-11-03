package com.marcos.estik.chat.application.command;

public record FileUpload(
    String fileName,
    Long fileSize,
    byte[] content
) {
    
}
