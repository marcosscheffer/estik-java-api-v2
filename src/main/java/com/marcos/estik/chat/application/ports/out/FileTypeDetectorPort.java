package com.marcos.estik.chat.application.ports.out;

public interface FileTypeDetectorPort {
    
    String detect(byte[] content, String fileName);

}
