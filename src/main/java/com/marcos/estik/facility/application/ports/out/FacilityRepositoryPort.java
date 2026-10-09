package com.marcos.estik.facility.application.ports.out;

import java.util.UUID;

import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface FacilityRepositoryPort {
    Facility save(Facility facility);

    Facility findFacilityById(UUID id);

    PageResult<Facility> SearchFacilityByName(String name, PageQuery page);

    Boolean existsById(UUID id);

}
