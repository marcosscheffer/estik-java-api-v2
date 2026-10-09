package com.marcos.estik.user.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.domain.UserTicket;

public interface LoadUserTicketsByRequesterPort {
    PageResult<UserTicket> findByRequester(UUID id, PageQuery page);
}
