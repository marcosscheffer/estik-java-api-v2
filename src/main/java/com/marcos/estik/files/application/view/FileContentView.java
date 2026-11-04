package com.marcos.estik.files.application.view;

public record FileContentView(
    String fileName,
    String fileType,
    byte[] content
) {
    
}
