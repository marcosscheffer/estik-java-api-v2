package com.marcos.estik.ticket.adapter.in.web.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

public record TicketResponse(
    UUID id,
    UUID chatId,
    TicketFacilityResponse facility,
    TicketUserResponse requester,
    List<TicketForwardedResponse> forwarded,
    TicketUserResponse regulator,
    List<TicketAssigneedsResponse> assigneeds,
    TicketSpecializationResponse specialization,
    String title,
    String description,
    StatusEnum status,
    PriorityEnum priority,
    Instant createdAt,
    Instant updatedAt
) {
    
}
