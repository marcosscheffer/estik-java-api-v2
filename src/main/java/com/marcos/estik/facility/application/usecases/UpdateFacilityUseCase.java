package com.marcos.estik.facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.command.UpdateFacilityCommand;
import com.marcos.estik.facility.application.ports.in.UpdateFacilityPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.facility.domain.Facility;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateFacilityUseCase implements UpdateFacilityPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public Facility execute(UpdateFacilityCommand command, UUID facilityId) {
        Facility facility = facilityRepositoryPort.findFacilityById(facilityId);
        facility.updateInfo(command.name(), command.code());
        return facilityRepositoryPort.save(facility);
    }
}
