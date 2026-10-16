package com.marcos.estik.facility.application.ports.in;

import java.util.UUID;

import com.marcos.estik.facility.application.command.UpdateFacilityCommand;
import com.marcos.estik.facility.domain.Facility;

public interface UpdateFacilityPort {

    Facility execute(UpdateFacilityCommand facility, UUID facilityId);

}
