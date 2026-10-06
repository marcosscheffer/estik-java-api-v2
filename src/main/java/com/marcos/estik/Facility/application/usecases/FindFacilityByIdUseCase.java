package com.marcos.estik.Facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.Facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.Facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.Facility.domain.Facility;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindFacilityByIdUseCase implements FindFacilityByIdPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public Facility execute(UUID id) {
        return facilityRepositoryPort.findFacilityById(id);
    }
}
