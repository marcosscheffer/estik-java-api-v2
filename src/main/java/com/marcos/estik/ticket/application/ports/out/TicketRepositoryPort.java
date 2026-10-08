package com.marcos.estik.ticket.application.ports.out;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    PageResult<Ticket> SearchTicketsByPriority(String title, PriorityEnum priority, PageQuery page);
    PageResult<Ticket> SearchTicketsByStatus(String title, StatusEnum status, PageQuery page);
    PageResult<Ticket> SearchTickets(String title, PageQuery page);
}
