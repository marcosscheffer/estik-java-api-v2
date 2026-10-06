package com.marcos.estik.Facility.application.ports.in;

import java.util.UUID;

import com.marcos.estik.Facility.domain.Facility;

public interface FindFacilityByIdPort {
    Facility execute(UUID id);
}
