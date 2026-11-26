package com.marcos.estik.solution.application.port.out;

import com.marcos.estik.shared.domain.file.FileUpload;
import com.marcos.estik.solution.application.view.SolutionFileInfo;

public interface LoadSolutionStoreFilePort {
    
    SolutionFileInfo execute(FileUpload file);

}
