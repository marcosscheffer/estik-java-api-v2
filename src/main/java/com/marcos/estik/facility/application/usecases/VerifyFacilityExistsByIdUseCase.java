package com.marcos.estik.facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.ports.in.VerifyFacilityExistsByIdPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class VerifyFacilityExistsByIdUseCase implements VerifyFacilityExistsByIdPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public void execute(UUID id) {
        if (!facilityRepositoryPort.existsById(id)) {
            throw new EntityNotFoundException("Facility not found");
        }
    }
    
}
