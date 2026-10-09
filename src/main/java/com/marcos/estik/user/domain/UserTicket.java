package com.marcos.estik.user.domain;

import java.time.Instant;
import java.util.UUID;

public record UserTicket (
    UUID id,
    UUID facilityId,
    String title,
    TicketTypeEnum ticketType,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt) {
    
}
