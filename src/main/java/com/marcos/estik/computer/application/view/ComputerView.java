package com.marcos.estik.computer.application.view;

import com.marcos.estik.computer.domain.Computer;

public record ComputerView(
    Computer computer,
    ComputerFacilityInfo facility,
    ComputerDepartamentInfo departament
) {
    
}
