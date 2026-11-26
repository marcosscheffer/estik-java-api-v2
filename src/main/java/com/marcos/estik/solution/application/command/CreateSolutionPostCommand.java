package com.marcos.estik.solution.application.command;

import com.marcos.estik.shared.domain.file.FileUpload;

public record CreateSolutionPostCommand(
    String title,
    String description,
    FileUpload file
     
) {
    
}
