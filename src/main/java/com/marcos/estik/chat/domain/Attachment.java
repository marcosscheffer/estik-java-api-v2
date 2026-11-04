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
    private UUID fileId;
    private Instant createdAt;

    public static Attachment create(
        UUID messageId,
        UUID fileId
    ) {
        return new Attachment(
            UUID.randomUUID(),
            messageId,
            fileId,
            Instant.now()
        );
    }
}
