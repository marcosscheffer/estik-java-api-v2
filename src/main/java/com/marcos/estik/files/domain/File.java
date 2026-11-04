package com.marcos.estik.files.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class File {
    private UUID id;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Instant createdAt;

    public static File create(
        String fileName,
        String fileType,
        Long fileSize,
        String fileUrl
    ) {
        return new File(
            UUID.randomUUID(),
            fileName,
            fileType,
            fileSize,
            fileUrl,
            Instant.now()
        );
    }
}
