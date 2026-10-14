package com.marcos.estik.computerhistory.application.command;

import java.util.UUID;

import com.marcos.estik.computerhistory.domain.DirectionEnum;

public record CreateComputerHistoryCommand(
    String serviceDescription,
    DirectionEnum direction,
    UUID computerId,
    UUID departamentId
) {
    
}
