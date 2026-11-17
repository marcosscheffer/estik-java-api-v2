package com.marcos.estik.chat.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.chat.ChatTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Chat {
    private UUID id;
    private ChatTypeEnum type;
    private Instant createdAt;

    public static Chat create(ChatTypeEnum type) {
        return new Chat(
            UUID.randomUUID(),
            type,
            Instant.now()
        );
    }
}
