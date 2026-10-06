package com.marcos.estik.Facility.application.ports.in;

import com.marcos.estik.Facility.application.command.CreateFacilityCommand;
import com.marcos.estik.Facility.domain.Facility;

public interface CreateFacilityPort {
    Facility execute(CreateFacilityCommand facility);
}
