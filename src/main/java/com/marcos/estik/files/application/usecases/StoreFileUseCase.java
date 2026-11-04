package com.marcos.estik.files.application.usecases;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.marcos.estik.files.application.ports.in.StoreFilePort;
import com.marcos.estik.files.application.ports.out.FileRepositoryPort;
import com.marcos.estik.files.application.ports.out.FileStoragePort;
import com.marcos.estik.files.application.ports.out.FileTypeDetectorPort;
import com.marcos.estik.files.domain.File;
import com.marcos.estik.files.domain.exception.FileMimeTypeInvalidException;
import com.marcos.estik.shared.domain.file.FileUpload;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class StoreFileUseCase implements StoreFilePort {

    private static final Set<String> ALLOWED_TYPES = Set.of(
        "image/png", "image/jpeg", "image/webp", "application/pdf"
    );

    private final FileRepositoryPort fileRepositoryPort;
    private final FileStoragePort fileStoragePort;
    private final FileTypeDetectorPort fileTypeDetectorPort;

    @Override
    public File execute(String dir, FileUpload file) {
        String mimeType = fileTypeDetectorPort.detect(file.content(), file.fileName());
        if (!ALLOWED_TYPES.contains(mimeType)) {
            throw new FileMimeTypeInvalidException("File type invalid");
        }
        
        String fileUrl = fileStoragePort.store(dir, file, mimeType);
        File fileSave = File.create(
            file.fileName(), 
            mimeType, 
            file.fileSize(), 
            fileUrl
        );
        fileRepositoryPort.save(fileSave);
        return fileSave;
    }
}
