package com.marcos.estik.files.adapter.out.filestorage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.out.FileStoragePort;
import com.marcos.estik.shared.domain.file.FileUpload;

import jakarta.persistence.EntityNotFoundException;

@Component 
public class FileStorageAdapter implements FileStoragePort {

    private final Path path;

    public FileStorageAdapter(@Value("${storage.chat.dir:uploads/chat}") String path) {
        this.path = Path.of(path).toAbsolutePath().normalize();
    }

    @Override
    public String store(String dir, FileUpload file, String fileType) {
        String storedName = UUID.randomUUID() + extensionOf(fileType);
        Path chatDir = path.resolve(dir.toString());

        try {
            Files.createDirectories(chatDir);
            Files.write(chatDir.resolve(storedName), file.content());
        } catch(IOException exception) {
            throw new UncheckedIOException("Failed to store file", exception);
        }

        return dir + "/" + storedName;

    }

    @Override
    public byte[] load(String fileUrl) {
        Path file = path.resolve(fileUrl).normalize();

        if (!file.startsWith(path)) {
            throw new SecurityException("Invalid file path");
        }

        if (!Files.exists(file)) {
            throw new EntityNotFoundException("File not found");
        }

        try {
            return Files.readAllBytes(file);
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed read file", exception);
        }

    }
    
    private String extensionOf(String fileType) {
        return switch (fileType) {
            case "image/png" -> ".png";
            case "image/jpeg" -> ".jpeg";
            case "image/webp" -> ".webp";
            case "application/pdf" -> ".pdf";
            default -> "";
        };
    }

}
