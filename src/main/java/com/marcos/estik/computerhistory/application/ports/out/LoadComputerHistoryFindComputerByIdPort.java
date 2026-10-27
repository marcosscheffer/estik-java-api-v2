package com.marcos.estik.computerhistory.application.ports.out;

import java.util.UUID;

import com.marcos.estik.computerhistory.application.view.ComputerHistoryComputerInfo;

public interface LoadComputerHistoryFindComputerByIdPort {
    
    ComputerHistoryComputerInfo execute(UUID id);

}
