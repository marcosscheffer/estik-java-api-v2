package com.marcos.estik.solution.application.command;

import com.marcos.estik.shared.domain.file.FileUpload;

public record UpdateSolutionPostCommand(
    String title,
    String description,
    FileUpload file
) {
    
}
