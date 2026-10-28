package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.domain.TicketMessage;

public interface TicketMessageRepositoryPort {

    TicketMessage save(TicketMessage ticketMessage);

    PageResult<TicketMessage> searchTicketMessage(UUID ticketId, PageQuery pageQuery);
}
