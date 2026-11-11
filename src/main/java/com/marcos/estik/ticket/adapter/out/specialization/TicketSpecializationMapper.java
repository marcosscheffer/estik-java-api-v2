package com.marcos.estik.ticket.adapter.out.specialization;

import org.mapstruct.Mapper;

import com.marcos.estik.specialization.domain.Specialization;
import com.marcos.estik.ticket.application.view.TicketSpecializationInfo;

@Mapper(componentModel = "spring")
public interface TicketSpecializationMapper {
    
    TicketSpecializationInfo toInfo(Specialization specialization);

}
