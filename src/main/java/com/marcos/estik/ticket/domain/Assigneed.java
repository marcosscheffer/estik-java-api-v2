package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Assigneed {
    UUID id;
    UUID userId;
    UUID ticketId;
    Instant createdAt;

    public static Assigneed create(
        UUID userId,
        UUID ticketId
    ) {
        return new Assigneed(
            UUID.randomUUID(), 
            userId, 
            ticketId, 
            Instant.now()
        );
    }
}
