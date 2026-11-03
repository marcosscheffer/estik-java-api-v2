package com.marcos.estik.chat.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Participant {
    private UUID id;
    private UUID chatId;
    private UUID userId;
    private Instant joinedAt;

    public static Participant create(UUID chatId, UUID userId) {
        return new Participant(
            UUID.randomUUID(), 
            chatId, 
            userId, 
            Instant.now()
        );
    }
}
