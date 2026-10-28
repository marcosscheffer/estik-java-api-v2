package com.marcos.estik.facility.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record FacilityTicketsResponse(
    UUID id,
    UUID facilityId,
    String title,
    TicketTypeEnum ticketType,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
}
