package com.marcos.estik.facility.adapter.out.ticket;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.domain.FacilityTicket;
import com.marcos.estik.ticket.domain.Ticket;

@Mapper(componentModel = "spring")
public interface FacilityTicketMapper {
    FacilityTicket toDomain(Ticket ticket);
}
