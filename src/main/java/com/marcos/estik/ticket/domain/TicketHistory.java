package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class TicketHistory {
    private UUID id;
    private UUID ticketId;
    private StatusEnum status;
    private TicketTypeEnum type;
    private Instant createdAt;

    public static TicketHistory create(
        UUID ticketId,
        StatusEnum status,
        TicketTypeEnum type
    ) {
        return new TicketHistory(
            UUID.randomUUID(), 
            ticketId, 
            status, 
            type, 
            Instant.now()
        );
    }
}
