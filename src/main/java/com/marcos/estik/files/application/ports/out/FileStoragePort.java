package com.marcos.estik.files.application.ports.out;

import com.marcos.estik.shared.domain.file.FileUpload;

public interface FileStoragePort {

    String store(String dir, FileUpload file, String fileType);

    byte[] load(String fileUrl);
    
}
