package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

import com.marcos.estik.computer.application.view.ComputerFacilityInfo;

public interface LoadComputerFindFacilityByIdPort {
    
    ComputerFacilityInfo execute(UUID id);

}
