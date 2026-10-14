package com.marcos.estik.computerhistory.application.command;

import java.util.UUID;

import com.marcos.estik.computerhistory.domain.DirectionEnum;
import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchComputerHistoryCommand(
    DirectionEnum direction,
    UUID computerId,
    UUID departamentId,
    PageQuery page
) {
    
}
