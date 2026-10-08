package com.marcos.estik.ticket.application.command;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;

public record SearchTicketsByTitleAndPriorityAndStatusCommand(
    String title,
    StatusEnum status,
    PriorityEnum priority,
    PageQuery page
) {
    
}
