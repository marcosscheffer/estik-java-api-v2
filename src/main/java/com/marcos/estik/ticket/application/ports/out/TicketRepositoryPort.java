package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    PageResult<Ticket> searchTicketsByPriority(String title, PriorityEnum priority, PageQuery page);
    PageResult<Ticket> searchTicketsByStatus(String title, StatusEnum status, PageQuery page);
    PageResult<Ticket> searchTickets(String title, PageQuery page);

    PageResult<Ticket> findTicketsByFacility(UUID facilityId, PageQuery page);
    PageResult<Ticket> findTicketsByRequester(UUID requesterId, PageQuery page);
    PageResult<Ticket> findTicketsByAssignee(UUID requesterId, PageQuery page);

    Ticket findTicketById(UUID id);

}
