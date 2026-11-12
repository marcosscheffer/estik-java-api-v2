package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

public record SearchTicketsFilter(
    String title,
    StatusEnum status,
    PriorityEnum priority,
    UUID specializationId,
    UUID facilityId,
    UUID requesterId,
    UUID regulatorId,
    UUID forwardedId,
    UUID assigneeId,
    UUID visibleUserId,
    UUID visibleSpecializationId,
    PageQuery page
) {
    
}
