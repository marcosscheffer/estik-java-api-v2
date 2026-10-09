package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.Ticket;

public interface FindTicketsByAssigneePort {
    PageResult<Ticket> execute(UUID id, PageQuery page);
}
