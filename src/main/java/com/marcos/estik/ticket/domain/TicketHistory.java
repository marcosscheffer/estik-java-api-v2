package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class TicketHistory {
    private UUID id;
    private UUID ticketId;
    private StatusEnum status;
    private UUID specializationId;
    private Instant createdAt;

    public static TicketHistory create(
        UUID ticketId,
        StatusEnum status,
        UUID specializationId
    ) {
        return new TicketHistory(
            UUID.randomUUID(), 
            ticketId, 
            status, 
            specializationId, 
            Instant.now()
        );
    }
}
