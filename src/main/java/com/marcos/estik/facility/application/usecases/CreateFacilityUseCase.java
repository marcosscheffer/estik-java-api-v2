package com.marcos.estik.facility.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.command.CreateFacilityCommand;
import com.marcos.estik.facility.application.ports.in.CreateFacilityPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.facility.domain.Facility;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateFacilityUseCase implements CreateFacilityPort {
    
    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public Facility execute(CreateFacilityCommand command) {
        Facility facility = Facility.create(command.name(), command.code());
        facilityRepositoryPort.save(facility);
        return facility;
    }

}
