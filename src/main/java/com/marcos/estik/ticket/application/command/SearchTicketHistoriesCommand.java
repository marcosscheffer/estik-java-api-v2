package com.marcos.estik.ticket.application.command;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public record SearchTicketHistoriesCommand(
    StatusEnum status, 
    TicketTypeEnum type, 
    PageQuery page
) {
    
}
