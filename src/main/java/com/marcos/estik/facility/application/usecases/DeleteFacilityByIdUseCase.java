package com.marcos.estik.facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.ports.in.DeleteFacilityByIdPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteFacilityByIdUseCase implements DeleteFacilityByIdPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public void execute(UUID id) {
        facilityRepositoryPort.deleteFacilityById(id);
    }
    
}
