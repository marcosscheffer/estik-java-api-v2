package com.marcos.estik.ticket.application.command;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

public record SearchTicketHistoriesCommand(
    StatusEnum status, 
    PageQuery page
) {
    
}
