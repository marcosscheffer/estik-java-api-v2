package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class Ticket {
    private UUID id;
    private String title;
    private String description;
    private TicketTypeEnum ticketType;
    private UUID requesterId;
    private UUID facilityId;
    private UUID assigneeId;
    private StatusEnum status;
    private PriorityEnum priority;
    private Instant createdAt;
    private Instant updatedAt;

    public static Ticket create(
        String title, 
        String description,
        TicketTypeEnum ticketType,
        UUID requesterId,
        UUID facilityId,
        PriorityEnum priority
    ) {
        return new Ticket(
            UUID.randomUUID(),
            title, 
            description, 
            ticketType,
            requesterId, 
            facilityId, 
            null,
            StatusEnum.OPEN, 
            priority, 
            Instant.now(), 
            Instant.now()
        );
    }
}
