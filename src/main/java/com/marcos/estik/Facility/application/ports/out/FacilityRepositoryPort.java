package com.marcos.estik.Facility.application.ports.out;

import java.util.UUID;

import com.marcos.estik.Facility.domain.Facility;

public interface FacilityRepositoryPort {
    Facility save(Facility facility);

    Facility findFacilityById(UUID id);
}
