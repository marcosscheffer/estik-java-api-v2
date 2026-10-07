package com.marcos.estik.facility.application.ports.in;

import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchFacilityByNamePort {
    PageResult<Facility> execute(String name, PageQuery page);
}
