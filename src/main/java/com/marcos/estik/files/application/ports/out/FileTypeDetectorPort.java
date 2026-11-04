package com.marcos.estik.files.application.ports.out;

public interface FileTypeDetectorPort {
    
    String detect(byte[] content, String fileName);

}
