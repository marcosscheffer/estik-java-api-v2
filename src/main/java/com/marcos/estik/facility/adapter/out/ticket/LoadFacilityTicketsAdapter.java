package com.marcos.estik.facility.adapter.out.ticket;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.out.LoadFacilityTicketsPort;
import com.marcos.estik.facility.domain.FacilityTicket;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsByFacilityPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class LoadFacilityTicketsAdapter implements LoadFacilityTicketsPort {

    private final SearchTicketsByFacilityPort searchTicketsByFacilityPort;
    private final FacilityTicketMapper facilityTicketMapper;

    @Override
    public PageResult<FacilityTicket> findByFacility(UUID id, PageQuery page) {
        return searchTicketsByFacilityPort.execute(id, page).map(facilityTicketMapper::toDomain);
    }
    
}
