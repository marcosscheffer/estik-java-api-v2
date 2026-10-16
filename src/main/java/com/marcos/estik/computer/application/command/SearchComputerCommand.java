package com.marcos.estik.computer.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchComputerCommand(
    String name,
    UUID facilityId,
    UUID departamentId,
    PageQuery page
) {
    
}
