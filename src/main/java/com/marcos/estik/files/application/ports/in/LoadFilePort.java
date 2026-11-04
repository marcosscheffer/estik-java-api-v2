package com.marcos.estik.files.application.ports.in;

import java.util.UUID;

import com.marcos.estik.files.application.view.FileContentView;

public interface LoadFilePort {
    
    FileContentView execute(UUID id, Long expires, String signature);

}
