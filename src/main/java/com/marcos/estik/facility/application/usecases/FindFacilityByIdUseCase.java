package com.marcos.estik.facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.facility.domain.Facility;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindFacilityByIdUseCase implements FindFacilityByIdPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public Facility execute(UUID id) {
        return facilityRepositoryPort.findById(id);
    }
}
