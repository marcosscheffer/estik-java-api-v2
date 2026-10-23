package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.view.ComputerView;

public interface FindComputerByIdPort {
    
    ComputerView execute(UUID id);

}
