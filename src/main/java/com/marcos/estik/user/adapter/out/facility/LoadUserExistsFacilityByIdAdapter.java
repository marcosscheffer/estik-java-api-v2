package com.marcos.estik.user.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserExistsFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserExistsFacilityByIdAdapter implements LoadUserExistsFacilityByIdPort {

    private final ExistsFacilityByIdPort existsFacilityByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsFacilityByIdPort.execute(id);
    }
    
}
