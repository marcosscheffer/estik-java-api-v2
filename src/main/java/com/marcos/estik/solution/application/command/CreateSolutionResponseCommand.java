package com.marcos.estik.solution.application.command;

import java.util.UUID;

import com.marcos.estik.shared.domain.file.FileUpload;

public record CreateSolutionResponseCommand(
    UUID solutionPostId,
    String message,
    FileUpload file
) {
    
}
