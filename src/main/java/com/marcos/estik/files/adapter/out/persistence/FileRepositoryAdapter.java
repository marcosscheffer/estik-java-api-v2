package com.marcos.estik.files.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.out.FileRepositoryPort;
import com.marcos.estik.files.domain.File;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class FileRepositoryAdapter implements FileRepositoryPort {

    private final JpaFileRepository jpaFileRepository;
    private final FilePersistenceMapper filePersistenceMapper;

    @Override
    public File save(File file) {
        FileEntity fileEntity = filePersistenceMapper.toEntity(file);
        FileEntity savedFileEntity = jpaFileRepository.save(fileEntity);
        return filePersistenceMapper.toDomain(savedFileEntity);
    }

    @Override
    public File findById(UUID id) {
        return filePersistenceMapper.toDomain(
            jpaFileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)   
        );
    } 
}
