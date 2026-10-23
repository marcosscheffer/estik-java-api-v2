package com.marcos.estik.ticket.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.view.TicketFacilityInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketFindFacilityByIdAdapter implements LoadTicketFindFacilityByIdPort {
    
    private final FindFacilityByIdPort findFacilityByIdPort;
    private final TicketFacilityMapper ticketFacilityMapper;
    
    @Override
    public TicketFacilityInfo execute(UUID id) {
        return ticketFacilityMapper.toFacilityInfo(findFacilityByIdPort.execute(id));
    }
}