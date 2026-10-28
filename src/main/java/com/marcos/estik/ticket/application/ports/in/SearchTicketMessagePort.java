package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.view.TicketMessageView;

public interface SearchTicketMessagePort {

    PageResult<TicketMessageView> execute(UUID ticketId, PageQuery page);
    
}
