package com.marcos.estik.facility.application.ports.in;

import com.marcos.estik.facility.application.command.CreateFacilityCommand;
import com.marcos.estik.facility.domain.Facility;

public interface CreateFacilityPort {
    Facility execute(CreateFacilityCommand facility);
}
