package com.marcos.estik.computer.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerFindFacilityByIdPort;
import com.marcos.estik.computer.application.view.ComputerFacilityInfo;
import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerFindFacilityByIdAdapter implements LoadComputerFindFacilityByIdPort {

    private final FindFacilityByIdPort findFacilityByIdPort;
    private final ComputerFacilityMapper computerFacilityMapper;
    
    @Override
    public ComputerFacilityInfo execute(UUID id) {
        return computerFacilityMapper.toInfo(findFacilityByIdPort.execute(id));
    }
    
}
