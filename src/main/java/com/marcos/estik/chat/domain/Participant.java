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
    private Boolean active;
    private Instant joinedAt;

    public static Participant create(UUID chatId, UUID userId) {
        return new Participant(
            UUID.randomUUID(), 
            chatId, 
            userId, 
            Boolean.TRUE,
            Instant.now()
        );
    }

    public void deactive() {
        this.active = Boolean.FALSE;
    }

    public void active() {
        this.active = Boolean.TRUE;
        this.joinedAt = Instant.now();
    }
}
