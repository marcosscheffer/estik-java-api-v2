package com.marcos.estik.files.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.files.application.ports.in.LoadFilePort;
import com.marcos.estik.files.application.ports.out.FileRepositoryPort;
import com.marcos.estik.files.application.ports.out.FileStoragePort;
import com.marcos.estik.files.application.ports.out.SignedUrlPort;
import com.marcos.estik.files.application.view.FileContentView;
import com.marcos.estik.files.domain.File;
import com.marcos.estik.files.domain.exception.FileLinkInvalidException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoadFileUseCase implements LoadFilePort {

    private final SignedUrlPort signedUrlPort;
    private final FileRepositoryPort fileRepositoryPort;
    private final FileStoragePort fileStoragePort;

    
    @Override
    @Transactional(readOnly = true)
    public FileContentView execute(UUID id, Long expires, String signature) {
        if (!signedUrlPort.isValid(id, expires, signature)) {
            throw new FileLinkInvalidException("Link invalid");
        }

        File file = fileRepositoryPort.findById(id);
        byte[] content = fileStoragePort.load(file.getFileUrl());

        return new FileContentView(
            file.getFileName(),
            file.getFileType(),
            content
        );
    }
}
