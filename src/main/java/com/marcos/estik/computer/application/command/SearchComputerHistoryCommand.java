package com.marcos.estik.computer.application.command;

import java.util.UUID;

import com.marcos.estik.computer.domain.DirectionEnum;
import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchComputerHistoryCommand(
    DirectionEnum direction,
    UUID computerId,
    UUID departamentId,
    PageQuery page
) {
    
}
