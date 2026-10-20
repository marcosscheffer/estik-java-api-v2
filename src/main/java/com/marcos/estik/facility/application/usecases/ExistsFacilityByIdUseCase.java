package com.marcos.estik.facility.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsFacilityByIdUseCase implements ExistsFacilityByIdPort {

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public Boolean execute(UUID id) {
        return facilityRepositoryPort.existsById(id);
    }
    
}
