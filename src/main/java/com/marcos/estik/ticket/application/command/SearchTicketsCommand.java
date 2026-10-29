package com.marcos.estik.ticket.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record SearchTicketsCommand(
    String title,
    StatusEnum status,
    PriorityEnum priority,
    TicketTypeEnum type,
    UUID facilityId,
    UUID requesterId,
    UUID regulatorId,
    UUID forwardedId,
    UUID assigneeId,
    PageQuery page
) {
    
}
