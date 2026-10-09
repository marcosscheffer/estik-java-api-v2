package com.marcos.estik.user.adapter.out.ticket;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.user.domain.UserTicket;

@Mapper(componentModel = "spring")
public interface LoadTicketMapper {
    UserTicket toDomain(Ticket ticket);
}
