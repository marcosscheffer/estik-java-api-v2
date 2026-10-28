package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.exception.TicketMessageInvalidException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class TicketMessage {
    private static final int MAX_BODY_LENGTH = 2000;

    private UUID id;
    private UUID ticketId;
    private UUID authorId;
    private String body;
    private Instant createdAt;

    public static TicketMessage create(
        UUID ticketId,
        UUID authorId,
        String body
    ) {
        String text = body == null ? "" : body.strip();
        if (text.isEmpty()) {
            throw new TicketMessageInvalidException("Message empty");
        }
        if (text.length() > MAX_BODY_LENGTH) {
            throw new TicketMessageInvalidException("Message too long");
        }
        
        return new TicketMessage(
            UUID.randomUUID(),
            ticketId,
            authorId,
            text,
            Instant.now()
        );
    }
    
}
