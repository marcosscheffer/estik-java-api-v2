package com.marcos.estik.chat.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Chat {
    private UUID id;
    private Instant createdAt;

    public static Chat create() {
        return new Chat(
            UUID.randomUUID(),
            Instant.now()
        );
    }
}
