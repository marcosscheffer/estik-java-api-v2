package com.marcos.estik.facility.application.ports.out;

import java.util.UUID;

import com.marcos.estik.facility.domain.FacilityTicket;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface LoadFacilityTicketsPort {
    PageResult<FacilityTicket> findByFacility(UUID id, PageQuery page);
}
