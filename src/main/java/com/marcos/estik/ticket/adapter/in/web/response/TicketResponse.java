package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record TicketResponse(
    UUID id,
    TicketFacilityResponse facility,
    TicketUserResponse requester,
    TicketUserResponse assignee,
    TicketTypeEnum ticketType,
    String title,
    String description,
    StatusEnum status,
    PriorityEnum priority,
    Instant createdAt,
    Instant updatedAt
) {
    
}
