package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    PageResult<Ticket> searchTickets(
        String title,
        UUID facilityId,
        UUID requesterId,
        UUID assigneeId,
        PriorityEnum priority,
        StatusEnum status,
        PageQuery page
    );

    Ticket findTicketById(UUID id);

}
