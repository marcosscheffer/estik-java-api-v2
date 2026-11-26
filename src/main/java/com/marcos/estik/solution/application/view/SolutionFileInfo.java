package com.marcos.estik.solution.application.view;

import java.util.UUID;

public record SolutionFileInfo(
    UUID id,
    String fileUrl,
    String fileType,
    Long fileSize,
    String fileName
) {
    
}
