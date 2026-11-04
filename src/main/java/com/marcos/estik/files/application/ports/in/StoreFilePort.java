package com.marcos.estik.files.application.ports.in;

import com.marcos.estik.files.domain.File;
import com.marcos.estik.shared.domain.file.FileUpload;

public interface StoreFilePort {

    File execute(String dir, FileUpload file); 
    
}
