package com.marcos.estik.computer.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerExistsFacilityByIdPort;
import com.marcos.estik.facility.application.ports.in.ExistsFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerExistsFacilityByIdAdapter implements LoadComputerExistsFacilityByIdPort {

    private final ExistsFacilityByIdPort existsFacilityByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsFacilityByIdPort.execute(id);
    }
}
