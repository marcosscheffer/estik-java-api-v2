package com.marcos.estik.chat.adapter.out.fileStorage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.command.FileUpload;
import com.marcos.estik.chat.application.ports.out.FileStoragePort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class FileStorageAdapter implements FileStoragePort {

    private final Path path;

    public FileStorageAdapter(@Value("${storage.chat.dir:uploads/chat}") String path) {
        this.path = Path.of(path).toAbsolutePath().normalize();
    }

    @Override
    public String store(UUID chatId, FileUpload file, String fileType) {
        String storedName = UUID.randomUUID() + extensionOf(fileType);
        Path chatDir = path.resolve(chatId.toString());

        try {
            Files.createDirectories(chatDir);
            Files.write(chatDir.resolve(storedName), file.content());
        } catch(IOException exception) {
            throw new UncheckedIOException("Failed to store file", exception);
        }

        return chatId + "/" + storedName;

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
