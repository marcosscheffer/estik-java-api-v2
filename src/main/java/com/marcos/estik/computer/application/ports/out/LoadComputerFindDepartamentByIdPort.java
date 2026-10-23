package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

import com.marcos.estik.computer.application.view.ComputerDepartamentInfo;

public interface LoadComputerFindDepartamentByIdPort {

    ComputerDepartamentInfo execute(UUID id);
    
}
