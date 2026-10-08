package com.marcos.estik.ticket.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadFacilityByIdAdapter implements LoadFacilityByIdPort{

    private final FindFacilityByIdPort findFacilityByIdPort;

    @Override
    public UUID findById(UUID id) {
        return findFacilityByIdPort.execute(id).getId();
    }
    
}
