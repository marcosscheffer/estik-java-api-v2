package com.marcos.estik.ticket.adapter.out.facility;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.ticket.application.view.TicketFacilityInfo;

@Mapper(componentModel = "spring")
public interface TicketFacilityMapper {
    
    TicketFacilityInfo toFacilityInfo(Facility facility);

}
