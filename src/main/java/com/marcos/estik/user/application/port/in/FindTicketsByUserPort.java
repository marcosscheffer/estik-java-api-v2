package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.domain.UserTicket;

public interface FindTicketsByUserPort {
    PageResult<UserTicket> execute(UUID userId, PageQuery page);
}
