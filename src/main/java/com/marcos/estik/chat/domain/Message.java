package com.marcos.estik.chat.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.chat.domain.exception.MessageNotUpdatableException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Message {
    private UUID id;
    private UUID chatId;
    private UUID senderId;
    private UUID replyToId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public static Message create(
        UUID chatId,
        UUID senderId,
        UUID replyToId,
        String content
    ) {
        return new Message(
            UUID.randomUUID(),
            chatId,
            senderId,
            replyToId,
            content,
            Instant.now(),
            Instant.now()
        );
    }

    public void updateContent(String content, UUID actorId) {

        if(Instant.now().isAfter(this.updatedAt.plusSeconds(1800))) { // 30 minutes
            throw new MessageNotUpdatableException("Message can only be updated within 30 minutes of last update");
        }

        if (actorId == null || !actorId.equals(this.senderId)) {
            throw new MessageNotUpdatableException("Only the sender can update the message content.");
        }

        this.content = content;
        this.updatedAt = Instant.now();
    }
}
