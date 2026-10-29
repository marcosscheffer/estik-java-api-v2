package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record TicketResponse(
    UUID id,
    TicketFacilityResponse facility,
    TicketUserResponse requester,
    TicketUserResponse forwarded,
    TicketUserResponse regulator,
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
