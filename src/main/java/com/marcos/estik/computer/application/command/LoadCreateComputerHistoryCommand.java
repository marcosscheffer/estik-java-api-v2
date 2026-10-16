package com.marcos.estik.computer.application.command;

import java.util.UUID;

public record LoadCreateComputerHistoryCommand(
    String serviceDescription,
    String direction,
    UUID computerId,
    UUID departamentId
) {
    
}
