package com.marcos.estik.user.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.user.domain.PriorityEnum;
import com.marcos.estik.user.domain.StatusEnum;
import com.marcos.estik.user.domain.TicketTypeEnum;

public record UserTicketResponse(
    UUID id,
    UUID facilityId,
    String title,
    TicketTypeEnum ticketType,
    PriorityEnum priority,
    StatusEnum status,
    Instant createdAt
) {
    
}
