package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    PageResult<Ticket> searchTickets(
        String title,
        UUID facilityId,
        UUID requesterId,
        UUID regulatorId,
        UUID forwardedId,
        Boolean forwarded,
        UUID assigneeId,
        PriorityEnum priority,
        TicketTypeEnum type,
        StatusEnum status,
        PageQuery page
    );

    Ticket findTicketById(UUID id);

    void deleteTicketById(UUID id);

}
