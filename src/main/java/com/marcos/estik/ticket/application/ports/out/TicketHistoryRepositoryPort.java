package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public interface TicketHistoryRepositoryPort {

    TicketHistory save(TicketHistory ticketHistory);

    PageResult<TicketHistory> searchTicketHistory(
        UUID ticketId, 
        StatusEnum status, 
        TicketTypeEnum type, 
        PageQuery page
    );


}
