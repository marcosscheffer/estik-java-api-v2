package com.marcos.estik.files.adapter.out.tika;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.out.FileTypeDetectorPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class FileTypeDetectorAdapter implements FileTypeDetectorPort {

    private final Tika tika;

    @Override
    public String detect(byte[] content, String fileName) {
        return tika.detect(content, fileName);
    }
    
}
