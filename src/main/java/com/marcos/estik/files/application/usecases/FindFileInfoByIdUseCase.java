package com.marcos.estik.files.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.files.application.ports.in.FindFileInfoByIdPort;
import com.marcos.estik.files.application.ports.out.FileRepositoryPort;
import com.marcos.estik.files.domain.File;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindFileInfoByIdUseCase implements FindFileInfoByIdPort {

    private final FileRepositoryPort fileRepositoryPort;

    @Override
    public File execute(UUID id) {
        return fileRepositoryPort.findById(id);
    }
}
