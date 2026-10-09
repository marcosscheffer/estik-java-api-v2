package com.marcos.estik.facility.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record FacilityTicket(
    UUID id,
    UUID facilityId,
    UUID requesterId,
    UUID assigneeId,
    String title,
    TicketTypeEnum ticketType,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
    
}
