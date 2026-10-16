package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.domain.Computer;

public interface FindComputerByIdPort {
    
    Computer execute(UUID id);

}
