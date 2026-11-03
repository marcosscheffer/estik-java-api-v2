package com.marcos.estik.chat.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Attachment {
    private UUID id;
    private UUID messageId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Instant createdAt;

    public static Attachment create(
        UUID messageId,
        String fileName,
        String fileType,
        Long fileSize,
        String fileLocation
    ) {
        return new Attachment(
            UUID.randomUUID(),
            messageId,
            fileName,
            fileType,
            fileSize,
            fileLocation,
            Instant.now()
        );
    }
}
