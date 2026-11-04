package com.marcos.estik.files.application.ports.in;

import java.util.UUID;

import com.marcos.estik.files.domain.File;

public interface FindFileInfoByIdPort {

    File execute(UUID id);
    
}
