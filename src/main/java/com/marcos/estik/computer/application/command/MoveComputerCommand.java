package com.marcos.estik.computer.application.command;

import java.util.UUID;

import com.marcos.estik.computer.domain.DirectionEnum;

public record MoveComputerCommand(
    UUID facilityId, 
    UUID departamentId,
    String serviceDescription,
    DirectionEnum direction

) {
    
}
