package com.marcos.estik.files.application.ports.out;

import java.util.UUID;

import com.marcos.estik.files.domain.File;

public interface FileRepositoryPort {

    File findById(UUID id);

    File save(File file);

}
