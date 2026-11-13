package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Forwarded {
    UUID id;
    UUID userId;
    UUID ticketId;
    Instant createdAt;
    

    public static Forwarded create(
        UUID userId,
        UUID ticketId
    ) {
        return new Forwarded(
            UUID.randomUUID(), 
            userId, 
            ticketId, 
            Instant.now()
        );
    }
}
