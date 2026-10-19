package com.marcos.estik.computer.application.command;

import java.util.UUID;

public record MoveComputerCommand(
    UUID facilityId, 
    UUID departamentId,
    String serviceDescription,
    String direction

) {
    
}
