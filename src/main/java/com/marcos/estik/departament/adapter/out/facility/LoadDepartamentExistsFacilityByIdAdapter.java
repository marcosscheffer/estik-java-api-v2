package com.marcos.estik.departament.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.out.LoadDepartamentExistsFacilityByIdPort;
import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadDepartamentExistsFacilityByIdAdapter implements LoadDepartamentExistsFacilityByIdPort {

    private final ExistsFacilityByIdPort existsFacilityByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsFacilityByIdPort.execute(id);
    }
    
}
