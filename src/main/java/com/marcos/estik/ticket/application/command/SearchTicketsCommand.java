package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;

public record SearchTicketsCommand(
    String title,
    StatusEnum status,
    PriorityEnum priority,
    UUID facilityId,
    UUID requesterId,
    UUID assigneeId,
    PageQuery page
) {
    
}
