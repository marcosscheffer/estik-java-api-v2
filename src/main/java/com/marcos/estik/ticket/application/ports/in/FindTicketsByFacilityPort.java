package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.Ticket;

public interface FindTicketsByFacilityPort {
    PageResult<Ticket> execute(UUID Id, PageQuery page);
}
