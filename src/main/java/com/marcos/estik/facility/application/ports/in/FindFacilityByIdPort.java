package com.marcos.estik.facility.application.ports.in;

import java.util.UUID;

import com.marcos.estik.facility.domain.Facility;

public interface FindFacilityByIdPort {
    Facility execute(UUID id);
}
