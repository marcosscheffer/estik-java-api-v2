package com.marcos.estik.ticket.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public record UpdateTicketRequest(
    String title,
    String description,
    StatusEnum status,
    UUID facilityId,
    UUID assigneeId,
    TicketTypeEnum ticketType,
    PriorityEnum priority
) {
    
}
