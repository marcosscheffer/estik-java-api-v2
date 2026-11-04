package com.marcos.estik.shared.domain.file;

public record FileUpload(
    String fileName,
    Long fileSize,
    byte[] content
) {
    
}
